/*
 * Copyright (C) 2005-2007 Michael Keith, University Of Manchester
 * 
 * email: mkeith@pulsarastronomy.net
 * www  : www.pulsarastronomy.net
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package bookkeepr.xml;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author kei041
 */
public class BookKeeprHandler extends DefaultHandler {

    Stack<XMLAble> objectStack = null;
    StringBuffer error = null;
    XMLAble returnObject = null;
    StringBuffer characters = null;
    Map<String, String> attributes = null;
    ComplicatedElementHandler complexHandler = null;
    String complexTag = null;

    public XMLAble getObject() {
        return returnObject;
    }

    public String getError() {
        return error.toString();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        characters.append(ch, start, length);
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        objectStack = null;
        error = null;
        characters = null;
        attributes = null;
        complexHandler = null;
        complexTag = null;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (complexHandler != null) {
            complexHandler.endTag(localName, characters);
            if (localName.equals(complexTag)) {
                complexHandler = null;
                complexTag = null;
            }
            return;
        }
        if (mangleName(localName).equals(this.objectStack.peek().getClassName())) {
            XMLAble popped = this.objectStack.pop();
            if (this.objectStack.empty()) {
                this.objectStack = null;
                this.returnObject = popped;
            } else {
                XMLAble obj = objectStack.peek();
                String mname = "add" + aliases((popped.getClassName()));
                try {

                    Method method = obj.getClass().getMethod(mname, Class.forName("bookkeepr.xmlable." + aliases(popped.getClassName())));
                    method.invoke(obj, popped);
                } catch (ClassNotFoundException ex) {
                    String msg = "Method " + mname + "(" + popped.getClass() + ") cannot be added to object " + obj.getClassName();
                    Logger.getLogger(BookKeeprHandler.class.getName()).log(Level.WARNING, msg, ex);
                    throw new SAXException(msg, ex);
                } catch (IllegalAccessException ex) {
                    String msg = "Method " + mname + "(" + popped.getClass() + ") cannot be added to object " + obj.getClassName();
                    Logger.getLogger(BookKeeprHandler.class.getName()).log(Level.WARNING, msg, ex);
                    this.error.append(msg);
                    throw new SAXException(msg, ex);
                } catch (IllegalArgumentException ex) {
                    String msg = "Method " + mname + "(" + popped.getClass() + ") cannot be added to object " + obj.getClassName();
                    Logger.getLogger(BookKeeprHandler.class.getName()).log(Level.WARNING, msg, ex);
                    this.error.append(msg);
                    throw new SAXException(msg, ex);
                } catch (InvocationTargetException ex) {
                    String msg = "Method " + mname + "(" + popped.getClass() + ") cannot be added to object " + obj.getClassName();
                    Logger.getLogger(BookKeeprHandler.class.getName()).log(Level.WARNING, msg + " 'invocation target exception'", ex);
                    this.error.append(msg);
                    throw new SAXException(msg, ex);
                } catch (NoSuchMethodException ex) {
                    String msg = "Method " + mname + "(" + popped.getClass() + ") cannot be added to object " + obj.getClassName();
                    Logger.getLogger(BookKeeprHandler.class.getName()).log(Level.WARNING, msg + " 'No such method'", ex);
                    this.error.append(msg);
                    throw new SAXException(msg, ex);
                } catch (SecurityException ex) {
                    String msg = "Method " + mname + "(" + popped.getClass() + ") cannot be added to object " + obj.getClassName();
                    Logger.getLogger(BookKeeprHandler.class.getName()).log(Level.WARNING, msg, ex);
                    this.error.append(msg);
                    throw new SAXException(msg, ex);
                }
            }
            return;
        } else {
            XMLAble obj = this.objectStack.peek();
            try {
                StringConvertable conv = obj.getXmlParameters().get(mangleName(localName));


                if (conv != null) {
                    if (conv.getTargetClass() == Void.class) {
                        characters = new StringBuffer();
                        return;
                    }
                    String mname = "set" + mangleName(localName);
                    Method method = obj.getClass().getMethod(mname, conv.getTargetClass());
                    Object o = null;
                    try {
                        o = conv.fromString(characters.toString(), attributes);
                    } catch (Exception e) {
                        String msg = "Badly formatted data " + characters + " for type " + conv.getTargetClass()+ " in class "+ obj.getClassName();
                        Logger.getLogger(BookKeeprHandler.class.getName()).log(Level.WARNING, msg);
                        this.error.append(msg);
                        throw new SAXException(msg, e);
                    }
                    method.invoke(obj, o);

                } else {
                    String msg = "Parameter " + localName + " cannot be applied to object " + obj.getClassName();
                    Logger.getLogger(BookKeeprHandler.class.getName()).log(Level.WARNING, msg);
                    this.error.append(msg);
                }
            } catch (IllegalAccessException ex) {
                String msg = "Parameter " + localName + " cannot be applied to object " + obj.getClassName();
                Logger.getLogger(BookKeeprHandler.class.getName()).log(Level.WARNING, null, ex);
                this.error.append(msg);
                throw new SAXException(msg, ex);
            } catch (IllegalArgumentException ex) {
                String msg = "Parameter " + localName + " cannot be applied to object " + obj.getClassName();
                Logger.getLogger(BookKeeprHandler.class.getName()).log(Level.WARNING, null, ex);
                this.error.append(msg);
                throw new SAXException(msg, ex);
            } catch (InvocationTargetException ex) {
                String msg = "Parameter " + localName + " cannot be applied to object " + obj.getClassName();
                Logger.getLogger(BookKeeprHandler.class.getName()).log(Level.WARNING, msg + " 'invocation target exception'", ex);
                this.error.append(msg);
                throw new SAXException(msg, ex);
            } catch (NoSuchMethodException ex) {
                String msg = "Parameter " + localName + " cannot be applied to object " + obj.getClassName();
                Logger.getLogger(BookKeeprHandler.class.getName()).log(Level.WARNING, msg + " 'No such method'", ex);
                this.error.append(msg);
                throw new SAXException(msg, ex);
            } catch (SecurityException ex) {
                String msg = "Parameter " + localName + " cannot be applied to object " + obj.getClassName();
                Logger.getLogger(BookKeeprHandler.class.getName()).log(Level.WARNING, msg, ex);
                this.error.append(msg);
                throw new SAXException(msg, ex);
            }
        }

    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        super.error(e);
        Logger.getLogger(BookKeeprHandler.class.getName()).log(Level.WARNING, e.toString(), e);
        this.error.append(e.toString());
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        super.fatalError(e);
        Logger.getLogger(BookKeeprHandler.class.getName()).log(Level.WARNING, e.toString(), e);
        this.error.append(e.toString());
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        this.error = new StringBuffer();
        this.objectStack = new Stack<XMLAble>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attr) throws SAXException {

        attributes = new HashMap<String, String>();
        for (int i = 0; i < attr.getLength(); i++) {
            attributes.put(attr.getLocalName(i), attr.getValue(i));
        }
        if (complexHandler != null) {
            complexHandler.startTag(localName, attributes);
            characters = new StringBuffer();

            return;
        }
        if (objectStack.empty() || objectStack.peek().getXmlSubObjects().contains(mangleName(localName)) || objectStack.peek().getXmlSubObjects().contains(aliases(mangleName(localName)))) {
            try {
                // we don't have an object.
                Class targetClass = Class.forName("bookkeepr.xmlable." + mangleName(localName));
                XMLAble obj = (XMLAble) targetClass.newInstance();
                if (obj instanceof XMLAbleComplex) {
                    ((XMLAbleComplex) obj).setXmlAttributes(attributes);
                }
                this.objectStack.push(obj);
            } catch (ClassNotFoundException ex) {
                error.append("Class " + localName + " does not exist!");
                throw new SAXException("Class " + localName + " does not exist!");
            } catch (Exception ex) {
                error.append("Class " + localName + " is invalid!");
                Logger.getLogger(BookKeeprHandler.class.getName()).log(Level.WARNING, ex.toString(), ex);
                throw new SAXException("Class " + localName + " is invalid!");
            }
        } else {
            if (objectStack.peek() instanceof XMLAbleComplex) {
                if (((XMLAbleComplex) objectStack.peek()).getXmlComplexElements().contains(mangleName(localName))) {
                    this.complexHandler = ((XMLAbleComplex) objectStack.peek()).getComplicatedElementHandler(mangleName(localName));
                    this.complexHandler.setParent((XMLAbleComplex) objectStack.peek());
                    this.complexTag = localName;
                    complexHandler.startTag(localName, attributes);
                }
            }
        }

        characters = new StringBuffer();
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
        Logger.getLogger(BookKeeprHandler.class.getName()).log(Level.WARNING, e.toString(), e);
        this.error.append(e.toString());
    }

    public static String mangleName(String name) {

        StringBuffer buf = new StringBuffer();
        boolean uc = true;
        for (char c : name.toCharArray()) {
            if (c == '_') {
                uc = true;
                continue;
            }
            if (uc) {
                buf.append(Character.toUpperCase(c));
            } else {
                buf.append(c);
            }
            uc = false;
        }
        return buf.toString();
    }

    public static String aliases(String name) {
        if (name.endsWith("Index")) {
            return "Index";
        } else {
            return name;
        }
    }
}

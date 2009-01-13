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

import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kei041
 */
public class XMLWriter {

    public static void write(OutputStream stream, XMLAble xmlable) {
        write(stream,xmlable,true);
    }
    public static void write(OutputStream stream, XMLAble xmlable, boolean xsl) {
        PrintStream out = new PrintStream(stream);
        out.println("<?xml version='1.0' ?>");
        if(xsl)out.println("<?xml-stylesheet type=\"text/xsl\" href=\"/web/xsl/" + xmlable.getClassName() + ".xsl\"?>");
        writeXmlAble(out, xmlable, 0);
    }

    private static void writeXmlAble(PrintStream out, XMLAble xmlable, int tabs) {
        StringBuffer tab = new StringBuffer();
        for (int i = 0; i < tabs; i++) {
            tab.append("\t");
        }
        out.print(tab);


        out.print("<" + XMLWriter.mangleName(xmlable.getClassName()));
        if (xmlable instanceof XMLAbleComplex) {
            Map<String, String> attr = ((XMLAbleComplex) xmlable).getXmlAttributes();
            if (attr != null) {
                for (String key : attr.keySet()) {
                    out.print(" " + key + "='" + attr.get(key) + "'");
                }
            }
        }
        out.print(">\n");

        tab.append("\t");
        ArrayList<String> keys = new ArrayList(xmlable.getXmlParameters().keySet());
        Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
        for (String s : keys) {
            try {
                String xmlname = XMLWriter.mangleName(s);

                StringConvertable conv = xmlable.getXmlParameters().get(s);
                if (conv.getTargetClass() == Void.class) {
                    return;
                }
                Method method = xmlable.getClass().getMethod("get" + BookKeeprHandler.mangleName(s), new Class[0]);
                Object obj = (method.invoke(xmlable, new Object[0]));
                if (obj == null) {
                    continue;
                }
                String xmlValue = conv.toString(obj);
                if (xmlValue != null) {
                    out.print(tab);
                    out.print("<" + xmlname);
                    Map<String, String> attr = conv.getKeyValueAttr(obj);
                    if (attr != null) {
                        for (String key : attr.keySet()) {
                            out.print(" " + key + "='" + attr.get(key) + "'");
                        }
                    }
                    out.print(">" + xmlValue + "</" + xmlname + ">\n");
                }
            } catch (IllegalAccessException ex) {
                Logger.getLogger(XMLWriter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(XMLWriter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(XMLWriter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(XMLWriter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(XMLWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for (String s : xmlable.getXmlSubObjects()) {
            try {
                String xmlname = XMLWriter.mangleName(s);
                Method method = xmlable.getClass().getMethod("get" + BookKeeprHandler.mangleName(s) + "List", new Class[0]);

                List list = (List) (method.invoke(xmlable, new Object[0]));
                if (list != null) {
                    for (Object o : list) {
                        if (o instanceof XMLAble) {
                            writeXmlAble(out, (XMLAble) o, tabs + 1);
                        }
                    }
                }

            } catch (IllegalAccessException ex) {
                Logger.getLogger(XMLWriter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(XMLWriter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(XMLWriter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(XMLWriter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(XMLWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (xmlable instanceof XMLAbleComplex) {
            XMLAbleComplex xmlablecomplex = (XMLAbleComplex) xmlable;
            for (String key : xmlablecomplex.getXmlComplexElements()) {
                ComplicatedElementHandler ceh = xmlablecomplex.getComplicatedElementHandler(key);
                ceh.setParent(xmlablecomplex);
                List<String> lines = ceh.createXMLLines();
                for (String line : lines) {
                    out.print(tab);
                    out.println(line);
                }
            }
        }
        tab = new StringBuffer();
        for (int i = 0; i < tabs; i++) {
            tab.append("\t");
        }
        out.print(tab);

        out.println("</" + XMLWriter.mangleName(xmlable.getClassName()) + ">");
    }

    public static String mangleName(String name) {
        StringBuffer buf = new StringBuffer();
        boolean uscore = false;
        for (char c : name.toCharArray()) {
            if (Character.isUpperCase(c)) {
                if (uscore) {
                    buf.append('_');
                }
                c = Character.toLowerCase(c);
            }
            buf.append(c);
            uscore = true;
        }
        return buf.toString();
    }
}

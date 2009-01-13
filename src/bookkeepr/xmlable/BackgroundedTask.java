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
package bookkeepr.xmlable;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author kei041
 */
public class BackgroundedTask implements Runnable {

    public enum TaskStatus {

        WAITING, RUNNING, COMPLETE, ERROR
    }
    Runnable target;
    StringWriter messages;
    TaskStatus status;
    int id;
    String name;

    public BackgroundedTask(String name) {
        this.status = TaskStatus.WAITING;
        this.messages = new StringWriter();
        this.name = name;
    }

    public void run() {
        this.status = TaskStatus.RUNNING;

        try {
            target.run();

        } catch (Exception exception) {
            this.status = TaskStatus.ERROR;
            target = null;
            return;
        }
        target = null;
        this.status = TaskStatus.COMPLETE;
    }

    public String getMessages() {
        return messages.toString();
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setTarget(Runnable target) {
        this.target = target;
    }

    public PrintWriter getWriter() {
        return new PrintWriter(messages);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
}

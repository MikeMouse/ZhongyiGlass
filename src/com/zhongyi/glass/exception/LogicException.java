package com.zhongyi.glass.exception;
/*
 * Copyright (C) 2010 mAPPn.Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/**
 * 逻辑异常
 * 
 * @author mappn
 * @date 2011-3-14
 * 
 */
public class LogicException extends Exception {

    private int statusCode;

    public LogicException(int statusCode) {
        super(String.valueOf(statusCode));
        this.statusCode = statusCode;
    }

    public LogicException(int statusCode, Throwable t) {
        super(String.valueOf(statusCode), t);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    private static final long serialVersionUID = -1518661053313597690L;

}

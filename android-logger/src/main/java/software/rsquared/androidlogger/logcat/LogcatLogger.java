/*
 * Copyright 2017 rSquared s.c. R. Orlik, R. Zajfert
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package software.rsquared.androidlogger.logcat;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import software.rsquared.androidlogger.ConfigSetter;
import software.rsquared.androidlogger.Level;
import software.rsquared.androidlogger.Logger;

/**
 * {@link Logger Logger} that send messages to Logcat
 * console
 *
 * @author Rafal Zajfert
 * @version 1.0.5 (26/04/2015)
 */
@SuppressWarnings("unused")
public class LogcatLogger extends Logger implements ConfigSetter<LogcatLoggerConfig> {

    private LogcatLoggerConfig config = new LogcatLoggerConfig();

    public LogcatLogger() {
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public LogcatLoggerConfig getConfig() {
        return config;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setConfig(@NonNull LogcatLoggerConfig config) {
        this.config = config;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void print(Level level, String message) {
        String tag = getTag(level);
        switch (level) {
            case ERROR:
                Log.e(tag, message);
                break;
            case INFO:
                Log.i(tag, message);
                break;
            case DEBUG:
                Log.d(tag, message);
                break;
            case VERBOSE:
                Log.v(tag, message);
                break;
            case WARNING:
                Log.w(tag, message);
                break;
        }
    }
}
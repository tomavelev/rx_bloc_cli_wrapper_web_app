package com.programtom.rx_bloc_cli_wrapper_web_app.views;

public abstract class Event<T> {
    public abstract void onEvent(T o);
}


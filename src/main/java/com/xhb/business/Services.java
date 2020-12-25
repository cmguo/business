package com.xhb.business;

import com.xhb.prism.http.HttpServices;

import java.util.List;

import okhttp3.Interceptor;

public class Services {

    public static BookService bookService(List<Interceptor> interceptors) {
        interceptors.add(new TicketInterceptor());
        interceptors.add(new HostInterceptor());
        return HttpServices.get(BookService.class,  interceptors,
                new BookService.Result());
    }
}

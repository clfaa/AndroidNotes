package com.clfaa.androidnotes.rxjava;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by changlifei on 16/4/26.
 */
public class RxHelloWorld {

    public void one(){
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello world");
            }
        });
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("one(): " + s);
            }
        };
        observable.subscribe(subscriber);
    }

    public void two(){
        Observable.just("hello world").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("two(): " + s);
            }
        });
    }

    public void three(){
        Observable.just("hello world").subscribe(s -> System.out.println("three()" + s));
    }
    public void four(){
        Observable.just("hello world").subscribe(System.out::println);
    }

}

package com.clfaa.androidnotes.rxjava;

import java.util.List;
import java.util.StringTokenizer;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
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


    public void helloMap(){
        Observable.just("hello, rx!").map(s -> s + ": map").subscribe(System.out::println);
    }

    /**
     * Observable.from 将一组信息逐一发送
     */
    public void helloForm(){
        Observable.from(new String[]{"from1","from2","from3"}).subscribe(System.out::println);
    }

    public void helloFlatMap(){
        Observable.just(new String[]{"hello", "hello1","hello2","hello3","hello88","hello5","hello10","hello333","hello100","hello1000"})
                .flatMap(strings -> Observable.from(strings))
                .flatMap(s -> Observable.just(s + "-FlatMap"))
                .subscribe(System.out::println);
    }

    /**
     * filter 过滤
     */
    public void helloFilter(){
        Observable.just(new String[]{"hello", "hello1","hello2","hello3","hello88","hello5","hello10","hello333","hello100","hello1000"})
                .flatMap(strings -> Observable.from(strings))
                .filter(s1 -> s1.length() > 6)
                .flatMap(s -> Observable.just(s + "-FlatMap"))
                .subscribe(System.out::println);
    }

    /**
     * take 个数限制
     */
    public void helloTake(){
        Observable.just(new String[]{"hello", "hello1","hello2","hello3","hello88","hello5","hello10","hello333","hello100","hello1000"})
                .flatMap(strings -> Observable.from(strings))
                .filter(s1 -> s1.length() > 6)
                .flatMap(s -> Observable.just(s + "-FlatMap"))
                .take(3)
                .subscribe(System.out::println);
    }


}

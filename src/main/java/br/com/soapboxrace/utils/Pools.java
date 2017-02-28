package br.com.soapboxrace.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Pools
{
    public static ExecutorService threadPool = Executors.newFixedThreadPool(240);
    public static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(140);
}

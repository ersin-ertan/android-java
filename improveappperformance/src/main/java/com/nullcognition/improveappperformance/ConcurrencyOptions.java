package com.nullcognition.improveappperformance;
// ersin 30/08/15 Copyright (c) 2015+ All rights reserved.

/*
Aside from the android native options like AsyncTask, and IntentService, its important to understand
the potential downfalls of using these methods.
Intent service - single threaded, one job, no job system, no cancelation
AsyncTask - don't care about result outside of UI, due to activity lifecycle if you don't cancel it,
an activity leak could occur
Executor Framework - thread pools, callbacks, futures, MapReduce tasks

Other - prefer newer components  like, toolbar and recycler view
have consistent and simple architecture, log callbacks results
*/


public class ConcurrencyOptions{
}

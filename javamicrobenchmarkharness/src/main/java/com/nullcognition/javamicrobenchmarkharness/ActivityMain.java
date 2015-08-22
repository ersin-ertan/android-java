package com.nullcognition.javamicrobenchmarkharness;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;


@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ActivityMain extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try{
			new Runner(new OptionsBuilder()
					.include(Benchmark.class.getName() + ".*")
					.forks(1)
					.warmupTime(TimeValue.seconds(5))
					.warmupIterations(3)
					.measurementTime(TimeValue.seconds(5))
					.measurementIterations(5)
					.build()).run();
		}
		catch(RunnerException e){
			e.printStackTrace();
		}
	}

	private double x = 2.0;

	@Benchmark
	public double standardInvSqrt(){
		return 1.0 / Math.sqrt(x);
	}

	@Benchmark
	public double fastInvSqrt(){
		return invSqrt(x);
	}

	static double invSqrt(double x){
		double xhalf = 0.5d * x;
		long   i     = Double.doubleToLongBits(x);
		i = 0x5fe6ec85e7de30daL - (i >> 1);
		x = Double.longBitsToDouble(i);
		x = x * (1.5d - xhalf * x * x);
		return x;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		int id = item.getItemId();
		if(id == R.id.action_timinglogger){
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}

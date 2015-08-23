package com.nullcognition.retrolambda;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class ActivityMain extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	// given a view
	public void oldSetGen(MyView myView){
		myView.setViewGenerator(new MyView.ViewGenerator(){
			@Override public MyView generateView(final View view){
				MyView myViewGen = new MyView(view.getContext());
				myViewGen.setBackgroundColor(Color.GREEN);
				return myViewGen;
			}
		});

		myView.viewGenerator.generateView(new View(this));
		// will generate green views as per definition in anonymous inner's definition

	}

	// lambda syntax with implicit MyView.ViewGenerator, input and return types are implied
	public void lambdaSetGen(MyView myView){
		myView.setViewGenerator(view -> {
					MyView myViewGen = new MyView(view.getContext());
					myViewGen.setBackgroundColor(Color.CYAN);
					return myViewGen;
				}
		);

		myView.viewGenerator.generateView(new View(this));
	}

	// input and return types are implied, moved instantiation into MyView,
	// view generator will always returning the same randomly chosen pre-coloured background of MyView
	public void methodRefSetGen(MyView myView){
		myView.setViewGenerator(MyView::createViewAndSetBackgroundColourAsRandom);

		myView.viewGenerator.generateView(new View(this));
	}


	// examples of multi variable lambda

	// lambda syntax but with multi variable interface to set... as
	public void lambdaSetGenMultiVariable(MyView myView){
		myView.setViewGeneratorWithColour((viewGenWColour, color) -> {
					MyView myViewGen = new MyView(viewGenWColour.getContext(), viewGenWColour.getSolidColor());
					// instead of only getting the context from the inputted view, we could query it
					// for more properties and create a new MyView(view.getContext, view.getColor, ...
					// based on MyViews constructor and the needs of the generation

					// therefor removing this method, and referring to the constructor to set colour
					myViewGen.setBackgroundColor(color);

					// interface parameter should then only be used for post construction initialization
					// if constructor is relatively small, else consider a the builder pattern

					return myViewGen;
				}
		);

		myView.viewGeneratorWithColour.generateViewWithColour(new View(this), Color.BLUE);
	}

	// method reference with implied multi variables
	public void methodRefSetGenMultiVariable(MyView myView){
		myView.setViewGeneratorWithColour(MyView.MyViewInputColourViewCreator::createViewAndSetBackgroundColourAsInput);

		// the new View need not be new and could be an existing view with properties to copy
		View existingView = new View(this);
		myView.viewGeneratorWithColour.generateViewWithColour(existingView, existingView.getSolidColor());
	}

}


class MyView extends View{ // implements ViewGenerator{

	Context                 context;
	ViewGenerator           viewGenerator;
	ViewGeneratorWithColour viewGeneratorWithColour;
	int                     color;

	public MyView(final Context context){
		super(context);
		this.context = context;
	}

	public MyView(final Context context, int color){
		super(context);
		this.context = context;
		this.color = color;
	}

	public void setViewGenerator(ViewGenerator viewGenerator){ this.viewGenerator = viewGenerator;}

	public void setViewGeneratorWithColour(ViewGeneratorWithColour viewGeneratorWithColour){
		this.viewGeneratorWithColour = viewGeneratorWithColour;
	}

	public void setBackgroundAsRandomColor(){
		super.setBackgroundColor(Color.argb(255, randomInt, randomInt, randomInt));
	}

	private Random random    = new Random();
	private int    randomInt = getRandom();

	// min-max syntax left for readability
	private int getRandom(){ return random.nextInt((255 - 0) + 1) + 0;}

	public static MyView createViewAndSetBackgroundColourAsRandom(final View view){
		MyView myView = new MyView(view.getContext());
		myView.setBackgroundAsRandomColor();
		return myView;
	}

	public static class MyViewInputColourViewCreator{

		public static MyView createViewAndSetBackgroundColourAsInput(final View view, int colour){
			MyView myView = new MyView(view.getContext());
			myView.setBackgroundColor(colour);
			return myView;
		}
	}

	// Exploratory Syntax Start -----------------------------------------------------

	ViewGen          viewGen; // should not be used
	ViewGenWithParam viewGenWithParam;

	public void setViewGen(ViewGen viewGen){ this.viewGen = viewGen;}

	public void setViewGenWithParam(ViewGenWithParam v){ this.viewGenWithParam = v;}

	@Deprecated
	public MyView(){super(new Activity());} // used only for the ::new an example syntax, to get a context


	public static class ViewMaker{

		public static final Boo boo;

		// not sure how this is used, see System.out::println;
		static{
			boo = ViewMaker.boo::getBool;
			boolean b = boo.getBool();
		}

		// using ::new requires the context, thus with views,
		// the ::new syntax is impossible, consider a stand along object that is used in an interface
		//  that contains one method that takes no arguments and returns an object,
		// hence the ViewGen.generateView() example is trying to emulate the syntax not the use
		public static ViewGen makeViewGen(){
			return new ViewGen(){
				@Override public MyView generateView(){
					MyView myView = new MyView(new Activity()); // ignore the MyView(this) context creation order
					myView.setViewGen(MyView::new); // this is deprecated, because it refers to the no arg constructor
					myView.setViewGenWithParam(MyView::new);
					// ^ works, because ViewGenWithParam interface is implicitly used with MyView(final Context context)
					// note the context argument is passed in automatically
					return myView;
				}
			}; // never use this does not work, showcasing the ::new concept syntax
		}

		public static boolean bool(){ return true;}
	}


	{
		MyView.ViewGen viewGen = MyView.ViewMaker.makeViewGen();
		MyView myView = viewGen.generateView();
		// or
		MyView.ViewGen viewGen2 = MyView::new;
		MyView.ViewGenWithParam viewGenerator = MyView::new;
	}


	interface Boo{

		boolean getBool();
	}


	interface ViewGen{

		MyView generateView();
	}


	interface ViewGenWithParam{

		MyView generateView(Context context);
	}

	// Exploratory Syntax End -----------------------------------------------------


	interface ViewGenerator{

		MyView generateView(View view);
	}


	interface ViewGeneratorWithColour{

		MyView generateViewWithColour(View view, int color);
	}
}

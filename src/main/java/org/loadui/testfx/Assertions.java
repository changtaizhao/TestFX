package org.loadui.testfx;

import org.hamcrest.Matcher;

import static org.hamcrest.MatcherAssert.assertThat;

public class Assertions
{
	@SuppressWarnings( "unchecked" )
	public static void assertNodeExists( Matcher<?> matcher )
	{
		GuiTest.find( ( Matcher<Object> )matcher );
	}

	public static void assertNodeExists( String query )
	{
		GuiTest.find( query );
	}

	public static <T> void verifyThat( T value, Matcher<? super T> matcher )
	{
		verifyThat( "", value, matcher );
	}

	public static <T> void verifyThat( String reason, T value, Matcher<? super T> matcher )
	{
		try
		{
			assertThat( reason, value, matcher );
		}
		catch( AssertionError e )
		{
			throw new AssertionError( e.getMessage() + " Screenshot saved as " + GuiTest.captureScreenshot().getAbsolutePath() , e );
		}
	}
}

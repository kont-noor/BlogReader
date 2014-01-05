package nt.kont.blog.reader;

import android.test.ActivityInstrumentationTestCase2;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class nt.kont.blog.reader.BlogReaderTest \
 * nt.kont.blog.reader.tests/android.test.InstrumentationTestRunner
 */
public class BlogReaderTest extends ActivityInstrumentationTestCase2<BlogReader> {

    public BlogReaderTest() {
        super("nt.kont.blog.reader", BlogReader.class);
    }

}

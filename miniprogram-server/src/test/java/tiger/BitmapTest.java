package tiger;

import org.junit.Test;
import org.roaringbitmap.RoaringBitmap;

/**
 * @Author Zenghu
 * @Date 2022年08月07日 18:20
 * @Description
 * @Version: 1.0
 **/
public class BitmapTest {


    @Test
    public void test01() {
        RoaringBitmap rr = RoaringBitmap.bitmapOf(1, 2, 3, 1000);

        int select = rr.select(3);

        System.out.println(select);

    }
}

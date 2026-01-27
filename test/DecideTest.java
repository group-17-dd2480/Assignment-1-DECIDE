import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DecideTest {

    /**
     * Test for the add method in Decide class.
     */
    @Test
    void addWorks() {
        assertEquals(5, Decide.add(2, 3));
    }

    static class setCMVTestDecideStub extends Decide {
        boolean[] ret = new boolean[15];
        @Override public boolean lic0(){ return ret[0]; }
        @Override public boolean lic1(){ return ret[1]; }
        @Override public boolean lic2(){ return ret[2]; }
        @Override public boolean lic3(){ return ret[3]; }
        @Override public boolean lic4(){ return ret[4]; }
        @Override public boolean lic5(){ return ret[5]; }
        @Override public boolean lic6(){ return ret[6]; }
        @Override public boolean lic7(){ return ret[7]; }
        @Override public boolean lic8(){ return ret[8]; }
        @Override public boolean lic9(){ return ret[9]; }
        @Override public boolean lic10(){ return ret[10]; }
        @Override public boolean lic11(){ return ret[11]; }
        @Override public boolean lic12(){ return ret[12]; }
        @Override public boolean lic13(){ return ret[13]; }
        @Override public boolean lic14(){ return ret[14]; }
    }


    /**
     * Tests that setCMV correctly maps each LIC functions return value
     * to the corresponding index in the CMV array.
     */
    @Test
    void setCMV_mapsEachLICToSameIndex() {
        setCMVTestDecideStub d = new setCMVTestDecideStub();
        d.CMV = new boolean[15];
    
        for (int i = 0; i < 15; i++) d.ret[i] = false;
        d.setCMV();
    
        int k = 7;
        d.ret[k] = true;
        d.setCMV();
    
        for (int i = 0; i < 15; i++) {
            if (i == k) assertTrue(d.CMV[i]);
            else assertFalse(d.CMV[i]);
        }
    }


}

import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    static CharacterComparator offByOne;
    /*
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    Uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/
    @Test
    public void testEqualChars() {
        OffByOne obo = new OffByOne();
        
        assertTrue(obo.equalChars('a', 'b'));
        assertTrue(obo.equalChars('r', 'q'));
        
        assertFalse(obo.equalChars('a', 'e'));
        assertFalse(obo.equalChars('z', 'a'));
        assertFalse(obo.equalChars('a', 'a'));
        assertTrue(obo.equalChars('&', '%'));
    }

    @Test
    public void testEqualCharsWithUpperCase() {
        OffByOne obo = new OffByOne();
        assertTrue(obo.equalChars('B', 'A'));
        assertTrue(obo.equalChars('M', 'N'));
        
        assertFalse(obo.equalChars('G', 'I'));
        assertFalse(obo.equalChars('X', 'Z'));
    }

    @Test
    public void testEqualCharsWithMixedCase() {
        OffByOne obo = new OffByOne();
        assertFalse(obo.equalChars('a', 'B'));
        assertFalse(obo.equalChars('C', 'd'));
    }

    @Test
    public void testEqualCharsWithNumbers() {
        OffByOne obo = new OffByOne();

        assertTrue(obo.equalChars('1', '2'));
        assertTrue(obo.equalChars('8', '7'));
        
        assertFalse(obo.equalChars('0', '9'));
        assertFalse(obo.equalChars('4', '6'));
    }

    @Test
    public void testEqualCharsWithSpecialCharacters() {
        OffByOne obo = new OffByOne();

        assertTrue(obo.equalChars('#', '$'));
        assertTrue(obo.equalChars('*', '+'));
        
        assertFalse(obo.equalChars('!', '@'));
        assertFalse(obo.equalChars('[', ']'));
    }

    @Test
    public void testEqualCharsWithEdgeCases() {
        OffByOne obo = new OffByOne();

        // 测试ASCII字符的边界情况
        assertTrue(obo.equalChars('!', '"'));
        assertFalse(obo.equalChars('~', '!'));
    }
}

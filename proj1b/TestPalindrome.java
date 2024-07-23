import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        Palindrome palindrome = new Palindrome();
        
        // 确认回文
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("noon"));
        
        // 确认非回文
        assertFalse(palindrome.isPalindrome("horse"));
        assertFalse(palindrome.isPalindrome("rancor"));
        
        // 边界情况
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));
        assertFalse(palindrome.isPalindrome("Aa"));
        assertFalse(palindrome.isPalindrome("aA"));

         // 测试更多的回文字符串
        assertTrue(palindrome.isPalindrome("madam"));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("tattarrattat"));

        // 测试更多的非回文字符串
        assertFalse(palindrome.isPalindrome("example"));
        assertFalse(palindrome.isPalindrome("palindrome"));
        assertFalse(palindrome.isPalindrome("testing"));

        // 测试大小写敏感性
        assertFalse(palindrome.isPalindrome("Noon"));
        assertFalse(palindrome.isPalindrome("RaceCar"));

        // 测试包含特殊字符的字符串
        assertTrue(palindrome.isPalindrome("a.b.a"));
        assertFalse(palindrome.isPalindrome("a,b,a!"));
    }
    
    @Test
    public void testIsPalindromeWithComparator() {
        Palindrome palindrome = new Palindrome();
        OffByOne obo = new OffByOne();
        
        assertTrue(palindrome.isPalindrome("flake", obo));
        assertFalse(palindrome.isPalindrome("racecar", obo));

        // 使用 OffByOne Comparator 测试更多的字符串
        assertTrue(palindrome.isPalindrome("abcb", obo));
        assertTrue(palindrome.isPalindrome("xzy", obo));
        assertFalse(palindrome.isPalindrome("aa", obo));
        assertFalse(palindrome.isPalindrome("xyzzyx", obo));

        // 测试单字符和空字符串
        assertTrue(palindrome.isPalindrome("b", obo));
        assertTrue(palindrome.isPalindrome("", obo));

        // 测试大小写敏感性
        assertFalse(palindrome.isPalindrome("Flake", obo));
        assertFalse(palindrome.isPalindrome("Racecar", obo));

        // 测试包含特殊字符的字符串
        assertTrue(palindrome.isPalindrome("a#b&c$b", obo));
        assertFalse(palindrome.isPalindrome("a,b,a!", obo));
    }
    
    @Test
    public void testEmptyAndSingleCharacterPalindromes() {
        Palindrome palindrome = new Palindrome();

        // 测试空字符串
        assertTrue(palindrome.isPalindrome(""));
        
        // 测试单字符字符串
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("Z"));
    }

    @Test
    public void testMixedCasePalindromes() {
        Palindrome palindrome = new Palindrome();

        // 测试混合大小写的回文字符串
        assertFalse(palindrome.isPalindrome("RaceCar"));
        assertFalse(palindrome.isPalindrome("Level"));
    }

    @Test
    public void testNumericAndSpecialCharacterPalindromes() {
        Palindrome palindrome = new Palindrome();

        // 测试包含数字的字符串
        assertTrue(palindrome.isPalindrome("12321"));
        assertFalse(palindrome.isPalindrome("12345"));
        
        // 测试包含特殊字符的字符串
        assertTrue(palindrome.isPalindrome("!@#@!"));
        assertFalse(palindrome.isPalindrome("!@#a@!"));
    }

    @Test
    public void testComplexPalindromes() {
        Palindrome palindrome = new Palindrome();

        // 测试更复杂的回文字符串
        assertTrue(palindrome.isPalindrome("A man, a plan, a canal, Panama".replaceAll("[\\W]", "").toLowerCase()));
        assertFalse(palindrome.isPalindrome("This is not a palindrome".replaceAll("[\\W]", "").toLowerCase()));
    }
    }

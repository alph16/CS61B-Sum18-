public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        // 实现方法，例如，检查字符是否相差1
        return Math.abs(x - y) == 1;
    }
}

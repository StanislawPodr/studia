import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TrieDictionaryTest {

    @Test
    void testInsertAndSearch() {
        TrieDictionary<String> trie = new TrieDictionary<>();
        trie.insert("apple", "fruit");
        trie.insert("app", "short");
        trie.insert("application", "long");
        trie.insert("ap", "long");
        trie.insert("app", "meh");
        trie.insert("appelstein", "meh");
        assertEquals("fruit", trie.search("apple"));
        assertEquals("meh", trie.search("app"));
        assertEquals("long", trie.search("application"));
        assertNull(trie.search("applet"));
    }

    @Test
    void testInsertOverride() {
        TrieDictionary<Integer> trie = new TrieDictionary<>();
        assertNull(trie.insert("test", 1));
        assertEquals(1, trie.search("test"));
        assertEquals(1, trie.insert("test", 2));
        assertEquals(2, trie.search("test"));
    }

    @Test
    void testEmptyKey() {
        TrieDictionary<String> trie = new TrieDictionary<>();
        assertNull(trie.insert("", "rootValue"));
        assertEquals("rootValue", trie.search(""));
    }

    @Test
    void testSearchNonExistent() {
        TrieDictionary<String> trie = new TrieDictionary<>();
        trie.insert("abc", "val1");
        trie.insert("abd", "val2");
        assertNull(trie.search("a"));
        assertNull(trie.search("abcd"));
    }

    @Test
    void testRemoveExistingKey() {
        TrieDictionary<String> trie = new TrieDictionary<>();
        trie.insert("hello", "world");
        trie.insert("hell", "fire");
        trie.insert("he", "short");
        trie.insert("hel", "prefix");
        trie.insert("hellix", "weird");
        trie.insert("helly", "odd");
        assertEquals("world", trie.remove("hello"));
        assertNull(trie.search("hello"));
        assertEquals("weird", trie.remove("hellix"));
        assertNull(trie.search("hellix"));
        assertEquals("prefix", trie.remove("hel"));
        assertNull(trie.search("hel"));
        assertEquals("fire", trie.search("hell"));
        assertEquals("short", trie.search("he"));
        assertEquals("odd", trie.search("helly"));
        assertNull(trie.remove("nonexistent"));
    }

    @Test
    void testRemoveWWithOnlyOneCharacter() {
        TrieDictionary<String> trie = new TrieDictionary<>();
        trie.insert("a", "alpha");
        assertEquals("alpha", trie.remove("a"));
        assertNull(trie.search("a"));
    }


    @Test
    void testRemoveNonExistentKey() {
        TrieDictionary<String> trie = new TrieDictionary<>();
        trie.insert("abc", "val");
        assertNull(trie.remove("abd"));
        assertNull(trie.remove("aba"));
        assertEquals("val", trie.search("abc"));
    }

    @Test
    void testRemoveEmptyKey() {
        TrieDictionary<String> trie = new TrieDictionary<>();
        trie.insert("", "rootVal");
        assertEquals("rootVal", trie.remove(""));
        assertNull(trie.search(""));
    }

    @Test
    void testInsertNullKey() {
        TrieDictionary<String> trie = new TrieDictionary<>();
        assertThrows(IllegalArgumentException.class, () -> trie.insert(null, "value"));
    }

    @Test
    void testInsertNullValue() {
        TrieDictionary<String> trie = new TrieDictionary<>();
        assertThrows(IllegalArgumentException.class, () -> trie.insert("key", null));
    }
}
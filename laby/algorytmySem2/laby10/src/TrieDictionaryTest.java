import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TrieDictionaryTest {

    @Test
    void testInsertAndSearch() {
        TrieDictionary<String> trie = new TrieDictionary<>();
        assertNull(trie.insert("apple", "fruit"));
        assertNull(trie.insert("app", "short"));
        assertNull(trie.insert("application", "long"));
        trie.insert("ap", "long");
        trie.insert("app", "meh");
        trie.insert("appelstein", "meh");
        assertEquals("fruit", trie.search("apple"));
        assertEquals("meh", trie.search("app"));
        assertEquals("long", trie.search("application"));
        assertNull(trie.search("applet"));
    }


    @Test
    void testInsertAndSearchWithEmptyString() {
        TrieDictionary<String> trie = new TrieDictionary<>();
        trie.insert("", "empty");
        assertEquals("empty", trie.search(""));
        assertNull(trie.search("nonexistent"));
    }

    @Test
    void testInsertAndSearchWithFirstElementsAsNewNodes() {
        TrieDictionary<String> trie = new TrieDictionary<>();
        assertNull(trie.insert("aa", "alpha"));
        assertNull(trie.insert("aaaa", "alpha"));
        assertEquals("alpha", trie.search("aa"));
        assertNull(trie.search("b"));
    }

    @Test
    void testInsertAndSearchWithMiddleElementsAsNewNodes() {
        TrieDictionary<String> trie = new TrieDictionary<>();
        assertNull(trie.insert("az", "alpha"));
        assertNull(trie.insert("ay", "alpha"));
        assertNull(trie.insert("ah", "alpha"));
        assertNull(trie.insert("ag", "alpha"));
        assertNull(trie.insert("ak", "alpha"));
        assertEquals("alpha", trie.search("az"));
        assertEquals("alpha", trie.search("ay"));
        assertEquals("alpha", trie.search("ah"));
        assertEquals("alpha", trie.search("ag"));
        assertEquals("alpha", trie.search("ak"));
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
    void testSearchWithPrefix() {
        TrieDictionary<String> trie = new TrieDictionary<>();
        trie.insert("hello", "world");
        trie.insert("hell", "fire");
        trie.insert("he", "short");
        trie.insert("hel", "prefix");
        trie.insert("hellix", "weird");
        trie.insert("helly", "odd");
        assertEquals("world", trie.search("hello"));
        assertEquals("fire", trie.search("hell"));
        assertEquals("short", trie.search("he"));
        assertEquals("prefix", trie.search("hel"));
        assertEquals("weird", trie.search("hellix"));
        assertEquals("odd", trie.search("helly"));
        assertNull(trie.search("nonexistent"));
    }

    @Test
    void testSearchWithOnlyOneCharacter() {
        TrieDictionary<String> trie = new TrieDictionary<>();
        trie.insert("a", "alpha");
        assertEquals("alpha", trie.search("a"));
        assertNull(trie.search("b"));
    }

    @Test
    void testSearchEmptyKey() {
        TrieDictionary<String> trie = new TrieDictionary<>();
        trie.insert("", "rootVal");
        assertEquals("rootVal", trie.search(""));
        assertNull(trie.search("nonexistent"));
    }

    @Test 
    void testSearchNullKey() {
        TrieDictionary<String> trie = new TrieDictionary<>();
        assertThrows(IllegalArgumentException.class, () -> trie.search(null));
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
    void testRemoveExistingKeyManyInRow() {
        TrieDictionary<String> trie = new TrieDictionary<>();
        trie.insert("abcccc", "val1");
        trie.insert("abdddd", "val2");
        trie.insert("abffff", "val3");
        assertEquals("val3", trie.remove("abffff"));
        assertEquals("val2", trie.remove("abdddd"));
        assertEquals("val1", trie.remove("abcccc"));
        assertNull(trie.search("abcccc"));
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

    @Test
    void testRemoveNullKey() {
        TrieDictionary<String> trie = new TrieDictionary<>();
        assertThrows(IllegalArgumentException.class, () -> trie.remove(null));
    }

    @Test
    void testRemoveNoValue() {
        TrieDictionary<String> trie = new TrieDictionary<>();
        assertNull(trie.remove("key2"));
    }


    @Test
    void testAllOperationsManyTimes() {
        TrieDictionary<String> trie = new TrieDictionary<>();

        // Insert many words
        assertNull(trie.insert("cat", "valCat"));
        assertNull(trie.insert("cater", "valCater"));
        assertNull(trie.insert("caterpillar", "valCaterpillar"));
        assertNull(trie.insert("catatonic", "valCatatonic"));
        assertNull(trie.insert("catalog", "valCatalog"));
        assertNull(trie.insert("dog", "valDog"));
        assertNull(trie.insert("do", "valDo"));
        assertNull(trie.insert("done", "valDone"));
        assertNull(trie.insert("doom", "valDoom"));
        assertNull(trie.insert("d", "valD"));
        assertNull(trie.insert("zebra", "valZebra"));
        assertNull(trie.insert("zeb", "valZeb"));
        assertNull(trie.insert("zero", "valZero"));
        assertNull(trie.insert("alpha", "valAlpha"));
        assertNull(trie.insert("alphabeta", "valAlphabeta"));

        // Verify insertions
        assertEquals("valCat", trie.search("cat"));
        assertEquals("valCater", trie.search("cater"));
        assertEquals("valCaterpillar", trie.search("caterpillar"));
        assertEquals("valCatatonic", trie.search("catatonic"));
        assertEquals("valCatalog", trie.search("catalog"));
        assertEquals("valDog", trie.search("dog"));
        assertEquals("valDo", trie.search("do"));
        assertEquals("valDone", trie.search("done"));
        assertEquals("valDoom", trie.search("doom"));
        assertEquals("valD", trie.search("d"));
        assertEquals("valZebra", trie.search("zebra"));
        assertEquals("valZeb", trie.search("zeb"));
        assertEquals("valZero", trie.search("zero"));
        assertEquals("valAlpha", trie.search("alpha"));
        assertEquals("valAlphabeta", trie.search("alphabeta"));

        // Remove some words
        assertEquals("valCat", trie.remove("cat"));
        assertEquals("valCater", trie.remove("cater"));
        assertEquals("valDog", trie.remove("dog"));
        assertEquals("valDo", trie.remove("do"));
        assertEquals("valZebra", trie.remove("zebra"));
        assertEquals("valAlpha", trie.remove("alpha"));

        // Check removals
        assertNull(trie.search("cat"));
        assertNull(trie.search("cater"));
        assertNull(trie.search("dog"));
        assertNull(trie.search("do"));
        assertNull(trie.search("zebra"));
        assertNull(trie.search("alpha"));

        // Verify others remain
        assertEquals("valCaterpillar", trie.search("caterpillar"));
        assertEquals("valCatatonic", trie.search("catatonic"));
        assertEquals("valCatalog", trie.search("catalog"));
        assertEquals("valDone", trie.search("done"));
        assertEquals("valDoom", trie.search("doom"));
        assertEquals("valD", trie.search("d"));
        assertEquals("valZeb", trie.search("zeb"));
        assertEquals("valZero", trie.search("zero"));
        assertEquals("valAlphabeta", trie.search("alphabeta"));
    }
}
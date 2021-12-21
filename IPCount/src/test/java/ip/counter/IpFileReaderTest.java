package ip.counter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IpFileReaderTest {

    private IpFileReader reader;
    IpCounter counter;
    private static final String NON_EXISTING_FILE = "C:\\Repositories\\IPUniqueCount\\IPCount\\src\\main\\resources\\nonexisting.txt";
    private static final String FILE_WITH_INVALID_DATA = "C:\\Repositories\\IPUniqueCount\\IPCount\\src\\main\\resources\\testfilenotpattern.txt";
    private static final String FILE_IP_ADDRESSES = "C:\\Repositories\\IPUniqueCount\\IPCount\\src\\main\\resources\\testipfile.txt";

    @BeforeEach
    void init() {
        IpConverter converter = new IpConverter();
        counter = new IpCounter();
        reader = new IpFileReader(converter, counter);
    }

    @Test
    void countUnique_ShouldThrowNoFileException_WhenInputNull() {
        assertThrows(IllegalArgumentException.class, () -> reader.readFile(null), "Input is null");
    }
    
    @Test
    void countUnique_ShouldThrowNoFileException_WhenInputNonExistingFile() {
        assertThrows(FileException.class, () -> reader.readFile(NON_EXISTING_FILE));
    }

    @Test
    void countUnique_ShouldThrowIllegalArgumentException_WhenInputFileContainsLineNotSuitPattern() {
        assertThrows(FileException.class, () -> reader.readFile(FILE_WITH_INVALID_DATA),
                "Data in \"testfilenotpattern.txt\" has invalid format!");
    }

    @Test
    void countUnique_ShouldReturnExpectedResult_WhenInputFileContainsIPAddresses() {
        long expected = 10L;
        reader.readFile(FILE_IP_ADDRESSES);
        long actual = counter.getCount();
        assertEquals(expected, actual);
    }
}
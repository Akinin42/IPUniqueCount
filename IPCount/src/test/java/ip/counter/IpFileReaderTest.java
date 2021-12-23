package ip.counter;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IpFileReaderTest {

    private IpFileReader reader;
    
    private static final String FILE_WITH_INVALID_DATA = "C:\\Repositories\\IPUniqueCount\\IPCount\\src\\main\\resources\\testfilenotpattern.txt";
    

    @BeforeEach
    void init() {
        IpConverter converter = new IpConverter();
        IpCounter counter = new IpCounter();
        reader = new IpFileReader(converter, counter);
    }

    @Test
    void countUnique_ShouldThrowNoFileException_WhenInputNull() {
        assertThrows(IllegalArgumentException.class, () -> reader.readFile(null), "Input is null");
    }
    
    @Test
    void countUnique_ShouldThrowNoFileException_WhenInputNonExistingFile() {
        assertThrows(FileException.class, () -> reader.readFile("nonexistingfile"));
    }

    @Test
    void countUnique_ShouldThrowIllegalArgumentException_WhenInputFileContainsLineNotSuitPattern() {
        assertThrows(FileException.class, () -> reader.readFile(FILE_WITH_INVALID_DATA),
                "Data in \"testfilenotpattern.txt\" has invalid format!");
    }
}
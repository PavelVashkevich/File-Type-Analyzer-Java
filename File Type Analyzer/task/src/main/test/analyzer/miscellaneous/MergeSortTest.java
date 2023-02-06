package analyzer.miscellaneous;

import analyzer.datatypes.FileDescriptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @ParameterizedTest
    @MethodSource("notSortFileDescriptorToSortFileDescriptor")
    void mergeSortShouldSortProperly(FileDescriptor[] notSort, FileDescriptor[] sort) {
        Assertions.assertArrayEquals(sort, MergeSort.sort(notSort));
    }

    private static List<Arguments> notSortFileDescriptorToSortFileDescriptor() {
        FileDescriptor[] notSort = new FileDescriptor[]{
                new FileDescriptor(30, "test 0","test 0"),
                new FileDescriptor(21, "test 1", "test 1"),
                new FileDescriptor(23, "test 2", "test 2"),
                new FileDescriptor(19, "test 3", "test 3"),
                new FileDescriptor(28, "test 4", "test 4"),
                new FileDescriptor(11, "test 5", "test 5"),
                new FileDescriptor(23, "test 6", "test 6" )};
        FileDescriptor[] sort = new FileDescriptor[]{
                new FileDescriptor(11, "test 5", "test 5"),
                new FileDescriptor(19, "test 3", "test 3"),
                new FileDescriptor(21, "test 1", "test 1"),
                new FileDescriptor(23, "test 2", "test 2"),
                new FileDescriptor(23, "test 6", "test 6" ),
                new FileDescriptor(28, "test 4", "test 4"),
                new FileDescriptor(30, "test 0","test 0")
        };
        return List.of(Arguments.arguments(notSort, sort));
    }
}
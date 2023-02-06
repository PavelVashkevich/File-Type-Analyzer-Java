package analyzer.miscellaneous;

import analyzer.datatypes.FileDescriptor;

import java.util.Arrays;
import java.util.Objects;

public class MergeSort {

    private MergeSort() {
        // No instance
    }

    public static FileDescriptor[] sort(FileDescriptor[] fileDescriptors) {
        if (Objects.isNull(fileDescriptors)) {
            return null;
        }

        if (fileDescriptors.length < 2) {
            return fileDescriptors;
        }

        int middle = fileDescriptors.length / 2;
        FileDescriptor[] fileDescriptorsLeft = Arrays.copyOfRange(fileDescriptors, 0, middle);
        FileDescriptor[] fileDescriptorsRight = Arrays.copyOfRange(fileDescriptors, middle, fileDescriptors.length);

        fileDescriptorsLeft = sort(fileDescriptorsLeft);
        fileDescriptorsRight = sort(fileDescriptorsRight);

        return mergeSort(fileDescriptorsLeft, fileDescriptorsRight);
    }

    private static FileDescriptor[] mergeSort(FileDescriptor[] fileDescriptorsLeft, FileDescriptor[] fileDescriptorsRight) {
        FileDescriptor[] sortedFileDescriptors = new FileDescriptor[fileDescriptorsLeft.length + fileDescriptorsRight.length];
        int indexLeft = 0;
        int indexRight = 0;
        int indexSorted = 0;
        while (indexSorted < sortedFileDescriptors.length) {
            if (indexLeft == fileDescriptorsLeft.length && indexRight < fileDescriptorsRight.length) {
                System.arraycopy(fileDescriptorsRight, indexRight, sortedFileDescriptors, indexSorted, sortedFileDescriptors.length - indexSorted);
                break;
            } else if (indexRight == fileDescriptorsRight.length && indexLeft < fileDescriptorsLeft.length) {
                System.arraycopy(fileDescriptorsLeft, indexLeft, sortedFileDescriptors, indexSorted, sortedFileDescriptors.length - indexSorted);
                break;
            }
            if (fileDescriptorsLeft[indexLeft].getPriority() > fileDescriptorsRight[indexRight].getPriority()) {
                sortedFileDescriptors[indexSorted] = fileDescriptorsRight[indexRight];
                indexRight++;
                indexSorted++;
            } else if (fileDescriptorsLeft[indexLeft].getPriority() < fileDescriptorsRight[indexRight].getPriority()) {
                sortedFileDescriptors[indexSorted] = fileDescriptorsLeft[indexLeft];
                indexLeft++;
                indexSorted++;
            } else {
                sortedFileDescriptors[indexSorted] = fileDescriptorsLeft[indexLeft];
                sortedFileDescriptors[++indexSorted] = fileDescriptorsRight[indexRight];
                indexLeft++;
                indexRight++;
                indexSorted++;
            }
        }
        return sortedFileDescriptors;
    }
}
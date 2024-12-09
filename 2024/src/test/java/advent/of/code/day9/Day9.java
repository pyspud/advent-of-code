package advent.of.code.day9;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import advent.of.code.Utils;

class Day9 {
    static final String SMALL_DISK_MAP = "12345";
    static final String SMALL_DISK_BLOCKS = "0..111....22222";

    static final String EXAMPLE_DISK_MAP = "2333133121414131402";
    static final String EXAMPLE_DISK_BLOCKS = "00...111...2...333.44.5555.6666.777.888899";
    static final String EXAMPLE_REARRANGED_BLOCKS = "0099811188827773336446555566..............";
    static final String EXAMPLE_REARRANGED_FILES = "00992111777.44.333....5555.6666.....8888..";

    // Part 1
    @Test
    void shouldMapSmallDiskMapToBlocks() {
        var blocks = DiskFragments.diskMapToBlocks(SMALL_DISK_MAP);
        assertThat(blocks).isEqualTo(SMALL_DISK_BLOCKS);
    }

    @Test
    void shouldMapExampleDiskMapToBlocks() {
        var blocks = DiskFragments.diskMapToBlocks(EXAMPLE_DISK_MAP);
        assertThat(blocks).isEqualTo(EXAMPLE_DISK_BLOCKS);
    }

    @Test
    void shouldMoveBlockToLeftMostFreeBlock() {
        var blocks = DiskFragments.diskMapToBlocks(EXAMPLE_DISK_MAP);
        var rearranged = DiskFragments.moveBlockToFree(blocks);
        assertThat(rearranged).isEqualTo(EXAMPLE_REARRANGED_BLOCKS);
    }

    @Test
    void shouldGetFileSystemCheckSumOfArrangedBlocks() {
        var check = DiskFragments.filesystemChecksum(EXAMPLE_REARRANGED_BLOCKS);
        assertThat(check).isEqualTo(1928);
    }

    @Test
    void whatIsTheResultingArrangedBlockFilesystemChecksum() {
        var input = Utils.inputLines("/advent/of/code/day9/inputPart1.txt");
        var blocks = DiskFragments.diskMapToBlocks(input.findFirst().get());
        var arranged = DiskFragments.moveBlockToFree(blocks);
        var result = DiskFragments.filesystemChecksum(arranged);
        assertThat(result).isEqualTo(6435922584968L);
    }

    // Part 2
    @Test
    void shouldMoveFileToLeftMostFreeBlock() {
        var blocks = DiskFragments.diskMapToBlocks(EXAMPLE_DISK_MAP);
        var rearranged = DiskFragments.moveFilesToFree(blocks);
        assertThat(rearranged).isEqualTo(EXAMPLE_REARRANGED_FILES);
    }

    @Test
    void shouldGetFileSystemCheckSumOfArrangedFiles() {
        var arranged = DiskFragments.moveFilesToFree(EXAMPLE_REARRANGED_FILES);
        var check = DiskFragments.filesystemChecksum(arranged);
        assertThat(check).isEqualTo(2858);
    }

    @Test
    void whatIsTheResultingArrangedFileFilesystemChecksum() {
        var input = Utils.inputLines("/advent/of/code/day9/inputPart1.txt");
        var blocks = DiskFragments.diskMapToBlocks(input.findFirst().get());
        var arranged = DiskFragments.moveFilesToFree(blocks);
        var result = DiskFragments.filesystemChecksum(arranged);
        assertThat(result).isEqualTo(6469636832766L);
    }
}

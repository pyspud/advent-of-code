package advent.of.code.day9;

interface DiskFragments {

    static String diskMapToBlocks(String map) {
        var blocks = new StringBuilder();

        int id = 0;
        boolean block = true;
        for (int p = 0; p < map.length(); p++) {
            var repeatString = String.valueOf(map.charAt(p));
            var repeat = Integer.parseInt(repeatString);

            if (block) {
                var idString = '0' + id++;
                blocks.repeat(idString, repeat);
            } else {
                blocks.repeat('.', repeat);
            }
            block = !block;
        }

        return blocks.toString();
    }

    static String moveBlockToFree(String blocks) {
        var rearranged = new StringBuilder(blocks);

        for (int n = rearranged.length() - 1; n > 0; n--) {
            var freeBlock = rearranged.indexOf(".");
            if (freeBlock > n) {
                break;
            }
            var block = rearranged.charAt(n);
            if (block != '.') {
                rearranged.setCharAt(n, '.');
                rearranged.setCharAt(freeBlock, block);
            }
        }
        return rearranged.toString();
    }

    static String moveFilesToFree(String blocks) {
        var rearranged = new StringBuilder(blocks);

        var maxId = rearranged.charAt(rearranged.length() - 1);
        for (char n = maxId; n > '0'; n--) {
            var idString = String.valueOf(n);
            var firstIndexOfId = rearranged.indexOf(idString);
            var lastIndexOfId = rearranged.lastIndexOf(idString)+1;
            var length = lastIndexOfId - firstIndexOfId;

            var freeBlock = rearranged.indexOf(".".repeat(length));

            if (freeBlock >= 0 && freeBlock < firstIndexOfId) {
                rearranged.replace(firstIndexOfId,firstIndexOfId+length, ".".repeat(length));
                rearranged.replace(freeBlock, freeBlock+length, idString.repeat(length));
            }
        }
        return rearranged.toString();
    }

    static long filesystemChecksum(String blocks) {
        long sum = 0l;
        for (int i = 0; i < blocks.length(); i++) {
            var block = blocks.charAt(i);
            if (block != '.') {
                var value = block-'0';
                sum += i * value;
            }
        }
        return sum;
    }

}

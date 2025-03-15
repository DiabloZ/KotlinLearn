package main.kotlin.leetcode.medium


private val solution = Solution()
fun main() {
    val test1 = arrayOf(
        charArrayOf('1', '1', '1', '1', '0'),
        charArrayOf('1', '1', '0', '1', '0'),
        charArrayOf('1', '1', '0', '0', '0'),
        charArrayOf('0', '0', '0', '1', '0')
    )
    val expectedResult = 2
    val result1 = solution.numIslands(test1)
    if (expectedResult != result1) {
        error("Not equal, expected - $expectedResult, result - $result1")
    }
}

private class Solution {

    val water = '0'
    val island = '1'

    fun numIslands(grid: Array<CharArray>): Int {
        val height = grid.size - 1
        val width = (grid.firstOrNull()?.size?.minus(1)) ?: return 0
        var islands = 0

        grid.forEachIndexed { indexColumn, chars ->
            chars.forEachIndexed { indexRow, c ->
                val isIsland = grid[indexColumn][indexRow] == island

                when {
                    indexRow == 0 && indexColumn == 0 -> {

                    }
                    indexRow == width && indexColumn == height -> {
                        val isAboveWater = grid[indexColumn -1][indexRow] == water
                        val isLeftWater = grid[indexColumn][indexRow -1] == water
                                                                      if (isIsland && isAboveWater && isLeftWater){
                            islands++
                        }

                    }
                    indexRow == 0 && indexColumn == height -> {

                    }
                    indexColumn == 0 && indexRow == width -> {

                    }
                    indexRow == 0 -> {

                    }
                    indexColumn == 0 -> {

                    }
                    indexRow == width -> {
                        val isAboveWater = grid[indexColumn -1][indexRow] == water
                        val isLeftWater = grid[indexColumn][indexRow -1] == water
                        val isBelowWater = grid[indexColumn + 1][indexRow] == water
                        if (isIsland && isAboveWater && isLeftWater && isBelowWater){
                            islands++
                        }
                    }
                    indexColumn == height -> {
                        val isAboveWater = grid[indexColumn -1][indexRow] == water
                        val isLeftWater = grid[indexColumn][indexRow -1] == water
                        val isRightWater = grid[indexColumn][indexRow + 1] == water
                        if (isIsland && isAboveWater && isLeftWater && isRightWater){
                            islands++
                        }
                    }
                }

            }
        }
        return islands
    }
}

/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [
 *   ['1','1','1','1','0'],
 *   ['1','1','0','1','0'],
 *   ['1','1','0','0','0'],
 *   ['0','0','0','0','0']
 * ]
 * Output: 1
 * Example 2:
 *
 * Input: grid = [
 *   ['1','1','0','0','0'],
 *   ['1','1','0','0','0'],
 *   ['0','0','1','0','0'],
 *   ['0','0','0','1','1']
 * ]
 * Output: 3
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] is '0' or '1'.
 */
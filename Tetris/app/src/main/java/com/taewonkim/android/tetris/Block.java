package com.taewonkim.android.tetris;

import java.util.Random;

/**
 * Created by 태원 on 2016-10-17.
 */
public class Block {

    private static final int ABS_X = 5;
    private static final int ABS_Y = 0;

    int x= 0 ;
    int y= 0;

    // 현재 회전 방향
    private int currentOrientation = 0;
    private int currentOrientationLimit = 0;

    // 블럭 그룹과 회전에 따른 블럭 형태를 나타내는 변수
    private int[][][] currentBlockGroup;
    private int[][] currentBlock;

    // 저장된 블록을 타입에 따라 가져오는 함수
    // 블럭의 회전 상태를 모두 포함한 블럭 그룹을 불러오는 함수
    // 회전 방향이 0 인 새로운 블럭을 가져온다.
    public void setBlock() {

        Random random = new Random();
        int type = random.nextInt(7);

        currentBlockGroup = blocks[type];
        currentOrientationLimit = currentBlockGroup.length;
        currentOrientation = 0;
        currentBlock = currentBlockGroup[currentOrientation];

        // 최초에 블럭이 세팅 되는 위치
        x = ABS_X; y= ABS_Y;
    }

    // 블럭의 회전방향을 선택하여 해당 블럭을 리턴해 주는 함수
    public void rotateBlock(){

        currentOrientation = currentOrientation + 1;
        currentOrientation = currentOrientation % currentOrientationLimit;
        currentBlock = currentBlockGroup[currentOrientation];

    }

    public int[][] getCurrentBlock(){
        return currentBlock;
    }


    int blocks[][][][] = {
            {
                    // Block I
                    {
                            {0, 1, 0, 0},
                            {0, 1, 0, 0},
                            {0, 1, 0, 0},
                            {0, 1, 0, 0}
                    },
                    {
                            {1, 1, 1, 1},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    }
            },
            // Block J
            {
                    {
                            {0, 0, 2, 0},
                            {0, 0, 2, 0},
                            {0, 2, 2, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 2, 0, 0},
                            {0, 2, 2, 2},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 2, 2, 0},
                            {0, 2, 0, 0},
                            {0, 2, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {2, 2, 2, 0},
                            {0, 0, 2, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    }
            },
            // Block L
            {
                    {
                            {0, 3, 0, 0},
                            {0, 3, 0, 0},
                            {0, 3, 3, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 3, 3, 3},
                            {0, 3, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 3, 3, 0},
                            {0, 0, 3, 0},
                            {0, 0, 3, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 0, 3, 0},
                            {3, 3, 3, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    }
            },
            // Block S
            {
                    {
                            {0, 4, 4, 0},
                            {4, 4, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 4, 0, 0},
                            {0, 4, 4, 0},
                            {0, 0, 4, 0},
                            {0, 0, 0, 0}
                    }
            },
            // Block Z
            {
                    {
                            {0, 5, 5, 0},
                            {0, 0, 5, 5},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 0, 5, 0},
                            {0, 5, 5, 0},
                            {0, 5, 0, 0},
                            {0, 0, 0, 0}
                    }
            },
            // Block T
            {
                    {
                            {0, 6, 0, 0},
                            {0, 6, 6, 0},
                            {0, 6, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {6, 6, 6, 0},
                            {0, 6, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 6, 0, 0},
                            {6, 6, 0, 0},
                            {0, 6, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 6, 0, 0},
                            {6, 6, 6, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    }
            },
            // Block O
            {
                    {
                            {0, 7, 7, 0},
                            {0, 7, 7, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    }
            },
    };

}


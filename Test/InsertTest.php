<?php

include "php/insertUser.php";
require 'php/dbConfig.php';


use PHPUnit\Framework\TestCase;

class InsertTest extends TestCase
{
    public function testprocessStatement()
    {
        $this->assertEquals(1, processStatement(1));
        $this->assertNotEquals(0, processStatement(1));
    }

    public function testMysqlExistsObject()
    {
        global $connect;
        $checkUser = userCheckStatement($connect);
        $param = $checkUser->param_count;

        $this->assertEquals(1, $param);
    }

    public function testMysqlInsertObject()
    {
        global $connect;
        $createUser = createStatementInsert($connect);
        $param = $createUser->param_count;

        $this->assertEquals(8, $param);
    }


}
?>

<?php

include "php/existsUser.php";
require 'php/dbConfig.php';


use PHPUnit\Framework\TestCase;

class ExistsTest extends TestCase
{
    public function testprocessStatement()
    {
        $this->assertEquals(0, processStatementExists(1));
        $this->assertNotEquals(1, processStatementExists(1));
    }


    public function testMysqlSelectObject()
    {
		$testVar = createQuerySearch("ab", "a");
        $this->assertContains("a", $testVar);
    	$this->assertContains("ab", $testVar);
    	$this->assertNotContains("DELETE", $testVar);
    	$this->assertNotContains("INSERT", $testVar);
    }


}
?>

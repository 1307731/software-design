<?php

include "php/getAverageSoldHouses.php";
require 'php/dbConfig.php';


use PHPUnit\Framework\TestCase;

class AverageSoldHousesTest extends TestCase
{
	
    public function testprocessStatement()
    {
    	global $connect;
    	$sql = createSelectAverage();
		$result = sendRequestSelectAverage($connect, $sql);
        $this->assertEquals(1, checkResult($result));
        $this->assertNotEquals(0, checkResult($result));
    }


    public function testMysqlSelectAverageStatement()
    {
		$testVar = createSelectAverage();
        $this->assertContains("AVG(", $testVar);
    	$this->assertNotContains("DELETE", $testVar);
    	$this->assertNotContains("INSERT", $testVar);
    }
    
    public function testMysqlSelectAverageObject()
    {
    	global $connect;
    	$sql = createSelectAverage();
		$result = sendRequestSelectAverage($connect, $sql);
		
		$fields = $result->field_count;
		$numRows = $result->num_rows;
        $this->assertEquals(6, $fields);
        $this->assertEquals(1, $numRows);
    }
    
    


}
?>

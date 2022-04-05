<?php
require_once "conn.php";
$sql = "select * from colleges";
if(!$conn->query($sql)){
    echo "Error in connecting to Database";
}
else{
    $result = $conn->query($sql);
    if($result->num_rows > 0){
        $return_arr['colleges'] = array();
        while($row = $result->fetch_array()){
            array_push($return_arr['colleges'],array(
                'id'=>$row['id'],
                'name'=>$row['name']
            ));
        }
        echo json_encode($return_arr);
    }
}
?>

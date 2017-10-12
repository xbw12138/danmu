<?php
$response = array();
if (isset($_POST['user'])&&isset($_POST['danmu_content'])) {
	$user = $_POST['user'];
    $danmu_content = $_POST['danmu_content'];
	$url="http://115.159.26.120/danmu/danmu.php?user=".$user."&content=".$danmu_content;
	access_url($url);
    $response["success"] = 1;
    $response["message"] = "YES";
    echo json_encode($response);
} else {
    $response["success"] = 0;
    $response["message"] = "NO";
    echo json_encode($response);
}
function access_url($url) {  
$file='';
    if ($url=='') return false;  
    $fp = fopen($url, 'r') or exit('Open url faild!');  
    if($fp){
    while(!feof($fp)) {  
        $file.=fgets($fp)."";
    }
    fclose($fp);  
    }
    return $file;
}
?>

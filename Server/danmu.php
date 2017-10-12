<?php
require_once ('XingeApp.php');
if (isset($_GET['user'])&&isset($_GET['content'])){
	$user = $_GET['user'];
    $content = $_GET['content'];
	var_dump(DemoPushAllDeviceMessage($user,$content));
}
//所有设备下发透传消息       注：透传消息默认不展示
function DemoPushAllDeviceMessage($user,$content)
{
	$push = new XingeApp(2100213470, '8d98979a476f23f029e77c62fc0caa7e');
	$mess = new Message();
	$mess->setTitle($user);
	$mess->setContent($content);
	$mess->setType(Message::TYPE_MESSAGE);
	$ret = $push->PushAllDevices(0, $mess);
	return $ret;
}

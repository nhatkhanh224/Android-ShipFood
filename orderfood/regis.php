<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {

    $name = $_POST['name'];
    $email = $_POST['email'];
    $password = $_POST['password'];
    $password=password_hash($password,PASSWORD_DEFAULT);

    require_once 'dbCon.php';

    $sql = "INSERT INTO users(name,email,password) VALUES('$name','$email','$password')";

    $response = mysqli_query($connect, $sql);

    if ($response) {
        $result["success"]="1";
        $result["message"]="success";
        echo json_encode($result);
        mysqli_close($connect);
    }else {
        $result["success"]="0";
        $result["message"]="error";
        echo json_encode($result);
        mysqli_close($connect);
    }
}

?>
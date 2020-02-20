<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>login</title>
    <!--bootstrap-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
    <!--summernote-->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.js"></script>



    <style>
        html {
            height: 100%;
            overflow: hidden;
        }



        body {
            margin: 0;
            padding: 0;
            perspective: 1px;
            transform-style: preserve-3d;
            height: 100%;
            overflow-x: hidden;
            overflow-y: auto;
            font-family: "Luna";
            background: #454c55;
        }

       
        


    </style>

</head>

<body>
        <div class="container">
    <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card card-signin my-5">
          <div class="card-body">
            <h5 class="card-title text-center">Sign In</h5>
            <br/>
            <form class="form-signin" action="./login.action" method="post">
              <div class="form-label-group">
                <input type="text" name="id" class="form-control" placeholder="ID" required autofocus>                
              </div>
              <br/>
              <div class="form-label-group">
                <input type="password" name="pw" class="form-control" placeholder="Password" required>                
              </div>
              <br/>
              <hr class="my-4">
              <br/>
              <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Sign in</button>
              <button class="btn btn-lg btn-warning btn-block text-uppercase" type="button" href="./signup">Sign up</button>                   
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>



</body>

</html>
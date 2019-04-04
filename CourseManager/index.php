<!DOCTYPE html>
<html>
<style>
/* CSS under the style tag to make the navigation bar is taken from: https://www.w3schools.com/csS/tryit.asp?filename=trycss_template4
*/

* {
  box-sizing: border-box;
}

body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
  font-size: 12px;
}

/* Style the side navigation */
.sidenav {
  height: 100%;
  width: 200px;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  background-color: #111;
  overflow-x: hidden;
}

/* Side navigation links */
.sidenav a {
  color: white;
  padding: 16px;
  text-decoration: none;
  display: block;
}

/* Remove the hover for Department on sidenav*/
.sidenav c {
  color: white;
  padding: 16px;
  text-decoration: none;
  display: block;
}

.sidenav c:hover {
  background-color: black;
  color: White;
}


/* Change color on hover */
.sidenav a:hover {
  background-color: #ddd;
  color: black;
}

/* Style the content */
.content {
  margin-left: 200px;
  padding-left: 20px;
}
</style>
<body>
<?php
echo '<div class="sidenav">';
echo '<c href="#">Department</c>';
$depFile = file('data/department.csv');
$depData = [];
// Use for loop to print each line of csv file 
foreach ($depFile as $line){
    // This an multi-dimension array, [x][y] where x is line # and y is value in comma separated line
    // course table headers are [id, name, description, # prereq, # antireq, lab info, hours, credits, parent id, prereq, antireq]
    $depData[] = str_getcsv($line);
   // echo $line;
}

for ($k = 2; $k < count($depData); $k++){
    // $k < count($depData) since there is empty line at end
    // start at line 2 to skip blank line and metadata/table headers in csv
    //echo '<a href="index.php">'.$depData[$k][1]. '</a>';
    echo '<a href="index.php?depInput='.$depData[$k][0].'">'.$depData[$k][1]. '</a>';
}


echo '</div>';
echo ('
<div class="content">
<br>
<table style="width:100%">
  <tr>
    <th align="left" >Course Name</th>
    <th align="left">Description</th> 
    <th align="left">Lab Info </th>
    <th align="left">Hours </th>
    <th align="left">Credits </th>
    <th align="left">Prequisite </th>
    <th align="left">Antirequisite</th>
  </tr>');
  echo('<tr>');
  echo '<div>'; 

  $courseFile = file('data/course.csv');
  $classData = [];
  // Use for loop to print each line of csv file 
  foreach ($courseFile as $line){
      // This an multi-dimension array, [x][y] where x is line # and y is value in comma separated line
      // course table headers are [id, name, description, # prereq, # antireq, lab info, hours, credits, parent id, prereq, antireq]
      $classData[] = str_getcsv($line);
     // echo $line;
  }

//echo '<div class="content">';
if(isset($_GET["depInput"])){
    $depInput = $_GET["depInput"];

    $progFile = file('data/program.csv');
    $progData = [];
    // Use for loop to print each line of csv file 
    foreach ($progFile as $line){
    // This an multi-dimension array, [x][y] where x is line # and y is value in comma separated line
    // course table headers are [id, name, description, # prereq, # antireq, lab info, hours, credits, parent id, prereq, antireq]
    $progData[] = str_getcsv($line);
   // echo $line;
}

// echo ('
// <div class="content">
// <br>
// <table style="width:100%">
//   <tr>
//     <th align="left" >Course Name</th>
//     <th align="left">Description</th> 
//     <th align="left">Lab Info </th>
//     <th align="left">Hours </th>
//     <th align="left">Credits </th>
//   </tr>');
//   echo('<tr><div>');



    for ($j = 2; $j <= count($progData); $j++){
        if($progData[$j][3] == $depInput){
            $progID = $progData[$j][0];
            // $courseFile = file('data/course.csv');
            // $classData = [];
            // // Use for loop to print each line of csv file 
            // foreach ($courseFile as $line){
            //     // This an multi-dimension array, [x][y] where x is line # and y is value in comma separated line
            //     // course table headers are [id, name, description, # prereq, # antireq, lab info, hours, credits, parent id, prereq, antireq]
            //     $classData[] = str_getcsv($line);
            //    // echo $line;
            // }
            //echo '<div>'; 

            
              for ($y = 2; $y <= count($classData); $y++){
                for ($x = 1; $x <= count($classData[$y]); $x ++){
                    //echo "I Like ". $classData[$x][2]. "\n" .'<br>';
                    if($classData[$y][8] == $progID){
                    if (($x != 3) && $x != 4 && $x <= 7){
                    echo("<td>". $classData[$y][$x]. "</td>");
                    }

                    if( $x == 9){
                        $prereq = $classData[$y][9];
                        $antireq = $classData[$y][10];
                        if($prereq != null){
                        for ($n = 2; $n <= count($classData); $n++){
                            if($prereq == $classData[$n][0]){
                                echo("<td>". $classData[$n][1]. "</td>");
                            }
                        }
                    }else{
                        echo("<td>". None. "</td>");}
        
                    // }
        
                    }
                    if($x == 10){
                        $prereq = $classData[$y][9];
                        $antireq = $classData[$y][10]; // check if null firset 
                        if($antireq != null){
                            for ($n = 2; $n <= count($classData); $n++){
                                if($antireq == $classData[$n][0]){
                                    echo("<td>". $classData[$n][1]. "</td>");
                                }
                            }
                        }
                        
                        else{
                                echo("<td>". None. "</td>");
        
                        }
                    }
                }
                    
                    }
                    echo ('</tr></div>'); // close the div for the row
            }
            
            //   echo ('</tr>
            // </table></div>'); // close the div for the table
            
        }
    }

    // $depInput is the ID of the DEPARTMENT 
    // find the program where department ID is parent


    // Use department to search the inputs
    // each course only belongs to one department
    // child of department is program


    //echo $depInput;
}else{ // default view to show all courses
    for ($f = 2; $f <= count($classData); $f++){
        for ($s = 1; $s <= count($classData[$f]); $s ++){
            //echo "I Like ". $classData[$x][2]. "\n" .'<br>';
            if (($s != 3) && $s != 4 && $s <= 7){
            echo("<td>". $classData[$f][$s]. "</td>");
            }
            if( $s == 9){
                $prereq = $classData[$f][9];
                $antireq = $classData[$f][10];
                if($prereq != null){
                for ($n = 2; $n <= count($classData); $n++){
                    if($prereq == $classData[$n][0]){
                        echo("<td>". $classData[$n][1]. "</td>");
                    }




                }
            }else{
                echo("<td>". None. "</td>");}

            // }

            }
            if($s == 10){
                $prereq = $classData[$f][9];
                $antireq = $classData[$f][10]; // check if null firset 
                if($antireq != null){
                    for ($n = 2; $n <= count($classData); $n++){
                        if($antireq == $classData[$n][0]){
                            echo("<td>". $classData[$n][1]. "</td>");
                        }
                    }
                }
                
                else{
                        echo("<td>". None. "</td>");

                }
             


            }
            




            
            }
            echo ('</tr></div>');
    }
    
    //   echo ('</tr>
    // </table></div>');
   // echo '</div';
}

echo ('</tr>
</table></div>');



// echo '</div>;';

?>




</body>
</html>
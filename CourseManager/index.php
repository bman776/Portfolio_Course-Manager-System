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
// Adding links to the side navigation bar
echo '<c href >Sort Courses By Department</c>';
echo '<a href = index.php > All Departments</a>';

$depFile = file('data/department.csv');
$depData = [];
// Use for loop to print each line of csv file 
foreach ($depFile as $line){
    // This an multi-dimension array, [x][y] where x is line # and y is value in comma separated line
    // course table headers are [id, name, description, # prereq, # antireq, lab info, hours, credits, parent id, prereq, antireq]
    $depData[] = str_getcsv($line);
}

for ($k = 2; $k < count($depData); $k++){
    // $k < count($depData) since there is empty line at end
    // start at line 2 to skip blank line and metadata/table headers in csv
    //echo '<a href="index.php">'.$depData[$k][1]. '</a>';
    echo '<a href="index.php?depInput='.$depData[$k][0].'">'.$depData[$k][1]. '</a>';
}

$progFile = file('data/program.csv'); // Get csv file
$progData = [];
// Use for loop to iterate through each line of the csv file 
foreach ($progFile as $line){
// This an multi-dimension array, [x][y] where x is line # and y is value in comma separated line
// course table headers are [id, name, description, # prereq, # antireq, lab info, hours, credits, parent id, prereq, antireq]
$progData[] = str_getcsv($line); // convert csv field data in each line to an array then push this array to array $progData
}  // Will be used to retrieve programs later
echo '<c href= #>Programs</c>';
echo '<a href="index.php?progInput=Programs">View Programs</a>';
echo '</div>';

if($_GET["progInput"] == 'Programs'){ // if the view programs link is pressed, if statement is true 

echo ('
<div class="content">
<br>
<table style="width:100%">
  <tr>
    <th align="left" >Program Name</th>
    <th align="left">Description</th> 
  </tr>');


  for ($i = 2; $i<= count($progData); $i++ ){
    // skip two lines again, skip the metadata and blank line
    // iterate over all lines in $progData
    echo('<tr>'); // start table row
    echo '<div>'; // start div
      for ($q = 1; $q <= 2; $q++){
          // skip id in $q = 1 and ignore the parent id, child id in $q = 3 and $q = 4

    echo("<td>". $progData[$i][$q]. "</td>");   // use for loop to print all data
      }

      echo '</div>'; // close div
      echo('</tr>'); // close table row 


  }

}

else{
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
    <th align="left">Antirequisite</th>
    <th align="left">Prequisite(s) </th>
  </tr>');
  echo('<tr>');
  echo '<div>'; 

  $courseFile = file('data/course.csv'); // Get the csv file
  $classData = [];
  // Use for loop to iterate through each line of the csv file 
  foreach ($courseFile as $line){
      $classData[] = str_getcsv($line); // convert csv field data in each line to an array then push this array to array $progData
     // echo $line;
  }

//echo '<div class="content">';
if(isset($_GET["depInput"])){ 
    // Ensure that department # is not empty - since # is used to sort courses 
    $depInput = $_GET["depInput"]; // get the user input when link is pressed, put in $depInput



    // Start at $j = 2 since there is metadata and empty line in csv files (so skip 2 lines)
    for ($j = 2; $j <= count($progData); $j++){
        if($progData[$j][3] == $depInput){
            // 4th item in array = parent of program, which is its department # 
            $progID = $progData[$j][0]; // get the program ID associated with department #

              // Use for loops to get all courses associated with the program ID 
              for ($y = 2; $y <= count($classData); $y++){
                for ($x = 1; $x <= count($classData[$y]); $x ++){
                    if($classData[$y][8] == $progID){
                    if (($x != 3) && $x != 4 && $x <= 7){
                        // skip # prereq (x = 3), # antireq (x = 4), skip prereq and antireq (x = 8, 9)
                    echo("<td>". $classData[$y][$x]. "</td>");
                    }

                    $preNum = $classData[$y][3];  // get number of prerequisites 

                    if($x == 9 + $preNum){ // 9 + # of prereq is where antirequisites start, since # of prereq starts at 9 (assume at least 1, so reduce # prereq by 1)
                        $antireq = $classData[$y][9 + $preNum]; // get course id of antireq
                        if($antireq != null){ // ensure antireq is not null
                            for ($n = 2; $n <= count($classData); $n++){
                                // skip first 2 lines, retrieve the courses 
                                if($antireq == $classData[$n][0]){ // Match the ID
                                    echo("<td>". $classData[$n][1]. "</td>"); // Find antirequisite name, print in table
                                }
                            }
                        }
                        else{ // if null print None
                                echo("<td>". None. "</td>");
        
                        }
        
        
                        if ($preNum == 0){ // Need at least one space for prerequisite to hold "None" 
                            $preNum = 1;
                        }
                        for ($z = 9; $z < 9 + $preNum; $z++){
                            $prereq = $classData[$y][$z];
                            if($prereq != null){ // ensure prereq is not null 
                            for ($n = 2; $n <= count($classData); $n++){
                                // skip first 2 lines, retrieve the courses
                                if($prereq == $classData[$n][0]){
                                    echo("<td>". $classData[$n][1]. "</td>"); // Find prerequisite name, print in table
                                }
                            }
                        }else{// if null print None
                            echo("<td>". None. "</td>");}
            
                         }
                        
                    }  


                }
                    
                    }
                    echo ('</tr></div>'); // close the div for the row
            }
            
            
        }
    }


}else{ // default view to show all courses
    // Use for loops to get all courses associated with the program ID 
    for ($f = 2; $f <= count($classData); $f++){
        for ($s = 1; $s <= count($classData[$f]); $s ++){
            if (($s != 3) && $s != 4 && $s <= 7){
            // skip # prereq (x = 3), # antireq (x = 4), skip prereq and antireq (x = 8, 9)
            echo("<td>". $classData[$f][$s]. "</td>");
            }
            $preNum = $classData[$f][3]; // get the # of prereq for course in row f 

            if($s == 9 + $preNum){ // 9 + # of prereq is where antirequisites start, since # of prereq starts at 9 (assume at least 1, so reduce # prereq by 1)
                $antireq = $classData[$f][9 + $preNum]; // get course id of antireq
                if($antireq != null){ // ensure antireq is not null
                    for ($n = 2; $n <= count($classData); $n++){
                        // skip first 2 lines, retrieve the courses 
                        if($antireq == $classData[$n][0]){ // match by ID
                            echo("<td>". $classData[$n][1]. "</td>"); // Find the antirequisite name, print in table
                        }
                    }
                }
                else{ // if null print None
                        echo("<td>". None. "</td>");

                }


              
                if ($preNum == 0){ // Need 1 space for none if there are no prerequisites 
                    $preNum = 1;
                }
                for ($z = 9; $z < 9 + $preNum; $z++){
                    // prereq start at column 9 ends at < 9 + # of prereq
                    $prereq = $classData[$f][$z]; // f is the row in the table
                    if($prereq != null){ // ensure prereq is not null 
                    for ($n = 2; $n <= count($classData); $n++){
                        // skip first 2 lines, retrieve the courses
                        if($prereq == $classData[$n][0]){ // match by ID
                            echo("<td>". $classData[$n][1]. "</td>"); // Find prerequisite, print in table
                        }
                    }
                }else{// if null print none
                    echo("<td>". None. "</td>");}
    
                 }
                
            }  

    
            }
            echo ('</tr></div>');
    }
}

echo ('</tr>    
</table></div>'); // close table row, table, div
}


?>
</body>
</html>

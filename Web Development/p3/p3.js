// Julie Depenyou
// UID:115911437

var play = 0;
do {
    play = Number(prompt("What would you like to do? (Choose 1-4)\n1.See some art.\n2.Play a game\n" +
        "3.Calculate interest\n4.Something else fun! "));
} while (play < 1 || play > 4);

if (play === 1) {

    var rows = Number(prompt("How many rows?"));
    var cols = Number(prompt("How many columns?"));

    function isPrime(num) {
        var i = 2;
        while (i < num) {
            if (num % i === 0) {
                return false;
            }
            i++;
        }
        return true;
    }

    function randomNumber(low, high) {
        return Math.floor(Math.random() * (high - low + 1)) + low;
    }

    if (rows > 0 && cols > 0) {

        var currentRow = 1;
        document.write("<table>");
        while (currentRow <= rows) {
            document.writeln("<tr>");
            var currentCol = 1;
            while (currentCol <= cols) {
                var sum = isPrime(currentCol + currentRow);
                // document.write("<td>_");
                if (sum === true) {
                    document.writeln("<td style='background-color: yellow'>");
                }
                else if (sum === false) {
                    var rand = randomNumber(0, 200);
                    var rand2 = randomNumber(0, 200);
                    var rand3 = randomNumber(0, 200);
                    // document.writeln("<td style='background-color: red'>_");
                    document.writeln("<td style='background-color: rgb(" + rand + "," + rand2 + "," + rand3 + ")'>");
                }
                document.write("</td>");
                currentCol = currentCol + 1;
            }
            document.write("</tr>");
            currentRow = currentRow + 1;
        }
        document.write("</table>");


    }

}

if (play === 2) {
    function randomNumber(low, high) {
        return Math.floor(Math.random() * (high - low + 1)) + low;
    }

    var value = randomNumber(1, 100);
    var askNumber = Number(prompt("I'm thinking of a number between 1 and 100\n Guess what it is (" + value
        + " is my number)"));
    var numberOfTries = 0;
    if (askNumber === value) {
        numberOfTries = numberOfTries + 1;
        alert("Correct Guess! You attempted once!");
    }
    while (askNumber !== value) {
        numberOfTries = numberOfTries + 1;
        if (askNumber < value) {
            var askNumber = Number(prompt(askNumber + " is too small. Guess another number"));
        }
        else if (askNumber > value) {
            var askNumber = Number(prompt(askNumber + " is too big. Guess another number"));
        }

    }
    alert("Correct Guess! You attempted " + numberOfTries + " times");
}

if (play === 3) {
    var principal = Number(prompt("Enter principal"));
    var rate = Number(prompt("Enter rate"));
    var years = Number(prompt("Enter number of years"));
    var compScheme = prompt("simple or compound?");
    if (compScheme === "simple") {
        var interest = principal * (rate / 100) * years;
        var int = interest.toFixed(2);
        var total = interest + principal;
        var tot = total.toFixed(2);
        document.writeln("<div class='interest'>");
        document.writeln("<table>");
        document.writeln("<tr>");
        document.writeln("<td>" + "<h2> Simple Interest: " + int + " </h2>" + "<h2> " + "Total:" + tot + " </h2>" + "</td>");
        document.writeln("</tr>");
        document.writeln("</table>");
        document.writeln("</div>")
    }
    else if (compScheme === "compound") {
        var x = 1 + (rate / 100);
        var totalAmount = principal * (Math.pow(x, years));
        var tot = totalAmount.toFixed(2);
        var interest = totalAmount - principal;
        var int = interest.toFixed(2);
        document.writeln("<div class='interest'>");
        document.writeln("<table>");
        document.writeln("<tr>");
        document.writeln("<td>" + "<h2> Compound Interest: " + int + " </h2>" + "<h2> " + "Total:" + tot + " </h2>" + "</td>");
        document.writeln("</tr>");
        document.writeln("</table>");
        document.writeln("</div>");
    }
}
/*
Option 4 is a Rock paper scissors shoot. numbers 1-3 will represent rock, paper, or scissors. the computer will
randomly generate numbers 1-3 while the user will be asked to input a value. winner will be determined using
standard rules of game: rock beat scissors, scissors beat paper, and paper beat rock. once the user gets
three wins the game will end and an artistic work will be rendered on page.
 */
if (play === 4) {
    alert("Rock, Paper, Scissors, Shoot!\n 1 is Rock.\n 2 is Paper\n" +
        "3 is Scissors\nRemember rock beat scissors, scissors beat paper, and paper beat rock");

    var numOfWins = 0;

    while (numOfWins < 3) {
        function randomNumber(low, high) {
            return Math.floor(Math.random() * (high - low + 1)) + low;
        }

        var comPlay = randomNumber(1, 3);

        var userPlay = 0;
        do {
            userPlay = Number(prompt("Enter 1 for Rock\nEnter 2 for Paper\nEnter 3 for Scissors" +
                "\nOnce you get three wins the game will end and you will see some art. Good Luck!"))
        } while (userPlay < 1 || userPlay > 3);

        if (comPlay === 1) {

            if (userPlay === 2) {
                alert("Paper beat Rock! You win!");
                numOfWins = numOfWins + 1;
            }
            else if (userPlay === 3) {
                alert("Rock beat Scissors. You lose");
            }
            else {
                alert("Rock ties with Rock");
            }
        }
        if (comPlay === 2) {

            if (userPlay === 1) {
                alert("Paper beat Rock. you lose");
            }
            else if (userPlay === 3) {
                alert("Scissors beat paper! You win!");
                numOfWins = numOfWins + 1;
            }
            else {
                alert("Paper ties with Paper");
            }
        }
        if (comPlay === 3) {

            if (userPlay === 1) {
                alert("Rock beat Scissors! You win!");
                numOfWins = numOfWins + 1;
            }
            else if (userPlay === 2) {
                alert("Scissors beat paper. You lose");
            }
            else {
                alert("Scissors ties with Scissors");
            }
        }

    }
    if (numOfWins = 3) {
        alert("You have won 3 times! Congrats");

        function randomNumber(low, high) {
            return Math.floor(Math.random() * (high - low + 1)) + low;
        }
        document.write("<h1 style='text-align: center'>Winner!</h1>")

        var rows = 10;
        var cols = 10;
        document.writeln("<table>");
        var currentRow = 1;
        while (currentRow <= rows) {
            document.writeln("<tr>");
            var currentCol = 1;
            while (currentCol <= cols) {
                var red = randomNumber(0, 255);
                var green = randomNumber(0, 255);
                var blue = randomNumber(0, 255);
                document.writeln("<th style= 'background-color: rgb(" + red + "," + green + "," + blue + ")'>" +
                    "<img src='PodLog.png' alt='' style='width: 50px' height='50px' border='2px'>");
                document.writeln("</th>");
                currentCol = currentCol + 1;
            }
            document.writeln("</tr>");
            currentRow = currentRow + 1;

        }
        document.writeln("</table>");
    }


}
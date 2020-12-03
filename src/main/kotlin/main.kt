import day1.ExpensesReport
import day2.PasswordValidator
import day3.Move
import day3.SlopeRider

val reader = FileReader()

fun main(args: Array<String>) {
    println(args.toString())
    day3()
}

fun day1() {
    println("Executing day1")
    val expensesReport = ExpensesReport(reader.readIntValuesFromFile("input_day1.txt"))
    println("Pairs summing to 2020: " + expensesReport.findPairMakingSum2020())
    println("Multiplications of pairs summing to 2020: " + expensesReport.findMultiplicationOfPairsMakingSum2020())
    println("Triples summing to 2020: " + expensesReport.findTriplesMakingSum2020())
    println("Multiplications of triples summing to 2020: " + expensesReport.findMultiplicationsOfTriplesMakingSum2020())
}

fun day2() {
    println("Executing day2")
    val passwordValidator = PasswordValidator(reader.readPasswordsWithPolicyFromFile("input_day2.txt"))
    println("Number of correct passwords: "+passwordValidator.numberOfValidPasswords())
}

fun day3() {
    println("Executing day3")
    val slopeRider = SlopeRider(reader.readSlopeRiderMap("input_day3.txt"))
    println("Start counting number of trees")
    val result11 = slopeRider.countTreesOnRoad(Move(1,1))
    val result31 = slopeRider.countTreesOnRoad(Move(3,1))
    val result51 = slopeRider.countTreesOnRoad(Move(5,1))
    val result71 = slopeRider.countTreesOnRoad(Move(7,1))
    val result12 = slopeRider.countTreesOnRoad(Move(1,2))

    val multiplication = result11*result31*result51*result71*result12.toLong()

    println("Trees on each route: 1-1 $result11, 3-1 $result31, 5-1 $result51, 7-1 $result71, 1-2 $result12")
    println("Multiplication of trees on the way down for all moves: $multiplication")
}
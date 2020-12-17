import day1.ExpensesReport
import day10.Joltage
import day11.SeatingSystem
import day12.Guidance
import day13.BusSchedule
import day14.BitMask
import day15.ElfGame
import day16.Ticket
import day16.TicketDecoder
import day17.ConwayCube
import day17.CubeState
import day2.PasswordValidator
import day3.Move
import day3.SlopeRider
import day4.DocumentValidation
import day5.SeatCalculator
import day7.BagList
import day8.Program
import day9.XmasEncoding

val reader = FileReader()

fun main(args: Array<String>) {
    args.forEach { println("ARG: $it") }
    day17()
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

fun day4() {
    println("Executing day4")
    val documentValidation = DocumentValidation(reader.readDocuments("input_day4.txt"))
    println("There is ${documentValidation.numberOfPassports()} passports within documents")
}

fun day5() {
    println("Executing day5")
    val seatCalculator = SeatCalculator()
    val seats = seatCalculator.calculateSeats(reader.readSeatCodes("input_day5.txt"))
    println("Highest seatID is ${seatCalculator.highestSeatID(seats.keys)}")
    println("Free seatIDs ${seatCalculator.missingSeatIDs(reader.readSeatCodes("input_day5.txt"))}")
}

fun day6() {
    println("Executing day6")
    val questionare = Questionnaire(reader.readQuestionnaires("input_day6.txt"))
    println("Number of yes questions: ${questionare.calculateAllYesAnswersInGroups()}")
}

fun day7() {
    println("Executing day7")
    val bagHandler = BagList(reader.readBags("input_day7.txt"))
    println("Number of bags in which we can put shiny gold bag: ${bagHandler.residesInBags("shiny gold")}")
    println("Number of bags inside shiny gold: ${bagHandler.countBagsInside("shiny gold")}")
}

fun day8() {
    println("Executing day8")
    val program = Program(reader.readProgram("input_day8.txt"))
    println("Program state just before overload: ${program.findAccumulatorValueBeforeLoop()}")
    println("Correct program state after fix: ${program.fixProgramWithLoop()}")
}

fun day9() {
    println("Executing day9")
    val encoding = XmasEncoding(25, reader.readLongValuesFromFile("input_day9.txt"))
    println("Values which could not be constructed from previous 25: ${encoding.findNumbersWhichAreNotSumOfPreviousPreambleValues()}")
    val contignuousSets = encoding.findContiguouSetOfAtLeastTwoNumbersWhichAreNotSumOfPreviousPreambleValues()
    val sumsOfLargestAndSmallest = contignuousSets.map { Pair(it.first, it.second.sorted()) }.map { Pair(it.first, it.second[0] + it.second[it.second.size-1]) }
    println("Sets which are producing wrong values ${contignuousSets} and sums of the lowest and highest values = ${sumsOfLargestAndSmallest}")
}

fun day10() {
    println("Executing day10")
    val joltage = Joltage(reader.readIntValuesFromFile("input_day10.txt"))
    val pairs = joltage.findNumberOfJoltageDifferences(joltage.input)
    println("Multiplied joltages: ${pairs[1]!! * pairs[3]!!}")
    println("Count of possible combinations: ${joltage.findNumberOfPossibleCombinations()}")
}

fun day11() {
    println("Executing day11")
    val seatState = SeatingSystem(reader.readSeatMap("input_day11.txt"))
    println("Number of occupied seats, when rotation stops: ${seatState.calculateOccupiedSeatsWhenChangesStops()}")
    println("Number of occupied seats, when rotation stops taking into consideration all visible seats: ${seatState.calculateOccupiedSeatsWhenChangesStopsPart2()}")
}

fun day12() {
    println("Executing day12")
    val guidance = Guidance()
    guidance.applyDirectionsToState(reader.readDirections("input_day12.txt"))
    println("Mannhatan distance after applying directions: ${guidance.calculateManhattanDistance()}")
}

fun day13() {
    println("Executing day13")
    val timestampAndLines = reader.readBusSchedule("input_day13.txt")
    val busSchedule = BusSchedule(timestampAndLines.first, timestampAndLines.second)
    println("Multiplication of bus line with lowest wait time: ${busSchedule.calculateEarlierBusWithWaitingTimeMultiplication()}")
}

fun day14() {
    println("Executing day14")
    val bitmask = BitMask(reader.readMemory("input_day14.txt"))
    println("Sum of non zero addresses in memory ${bitmask.decodeValuesAndSumNonZeroMemory()}")
    println("Sum of non zero addresses in memory after applying mask to memory bank ${bitmask.decodeMemoryAddressAndSumNonZeroValues()}")
}

fun day15() {
    println("Executing day15")
    val elfGame = ElfGame()
    println("Value of 2020th element of elf game is ${elfGame.play(listOf(0,14,1,3,7,9), 2020)}")
    println("Value of 30000000th element of elf game is ${elfGame.play(listOf(0,14,1,3,7,9), 30000000)}")
}

fun day16() {
    println("Executing day16")
    val triple = reader.readRulesWithTickets("input_day16.txt")
    val ticketDecoder = TicketDecoder(triple.first, triple.second, triple.third)
    println("Sum of not valid values among others tickets: ${ticketDecoder.sumOfNotValidValues()}")
    println("Multiplication of not valid values among others tickets: ${ticketDecoder.multiplyDepartureValuesFromMyTicket()}")
}

fun day17() {
    println("Executing day17")
    val conwayCube = ConwayCube(reader.readCubeSlice("input_day17.txt"))
    println("Active cubes left after 6 cycles: ${conwayCube.cubesLeftInStateAfterCycles(CubeState.ACTIVE, 7)}")
}
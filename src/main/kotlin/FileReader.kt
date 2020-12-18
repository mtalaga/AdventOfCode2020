import day11.SeatState
import day12.Action
import day12.Direction
import day12.Move
import day14.BitMaskCharacter
import day14.Memory
import day16.RuleList
import day16.Ticket
import day17.CubeState
import day2.PasswordWithPolicy
import day4.Document
import day7.Bag
import day8.Operation
import java.io.File
import java.util.stream.Collectors
import javax.swing.text.MaskFormatter

class FileReader() {
    fun readIntValuesFromFile(path: String): List<Int> {
        val file = returnFileIfExists(path)

        if (file != null) {
            val stringLines = file.readLines()
            return stringLines.stream().map { it.toInt() }.collect(Collectors.toList())
        }

        return emptyList()
    }

    fun readLongValuesFromFile(path: String): List<Long> {
        val file = returnFileIfExists(path)

        if (file != null) {
            val stringLines = file.readLines()
            return stringLines.stream().map { it.toLong() }.collect(Collectors.toList())
        }

        return emptyList()
    }

    fun readPasswordsWithPolicyFromFile(path: String): List<PasswordWithPolicy> {
        val file = returnFileIfExists(path)

        if (file != null) {
            val stringLines = file.readLines()
            return stringLines.stream()
                .filter { it != "" }
                .map {
                    val separate = it.split(" ")
                    val occurences = separate[0].split("-")
                    PasswordWithPolicy(occurences[0].toInt(), occurences[1].toInt(), separate[1][0], separate[2])
                }.collect(Collectors.toList())
        }
        return emptyList()
    }

    fun readSlopeRiderMap(path: String): List<CharArray> {
        val file = returnFileIfExists(path)

        if (file != null) {
            val stringLines = file.readLines()
            return stringLines.map { it.toCharArray() }
        }

        return emptyList()
    }

    fun readDocuments(path: String): List<Document> {
        val file = returnFileIfExists(path)

        if (file != null) {
            val result = mutableListOf<Document>()
            val stringLines = file.readLines()
            var documentData = ""
            for (line in stringLines) {
                if (line.isBlank()) {
                    result.add(createDocument(documentData))
                    documentData = ""
                } else {
                    if (!documentData.isBlank()) {
                        documentData += " "
                    }
                    documentData += line
                }
            }
            result.add(createDocument(documentData))
            return result
        }

        return emptyList()
    }

    fun readStringLines(path: String): List<String> {
        val file = returnFileIfExists(path)

        if (file != null) {
            return file.readLines()
        }

        return emptyList()
    }

    fun readQuestionnaires(path: String): List<Group> {
        val file = returnFileIfExists(path)

        if (file != null) {
            val result = mutableListOf<Group>()
            val stringLines = file.readLines()
            var group = mutableListOf<String>()
            for (line in stringLines) {
                if (line.isBlank()) {
                    result.add(Group(group))
                    group = mutableListOf()
                } else {
                    group.add(line)
                }
            }
            result.add(Group(group))
            return result
        }

        return emptyList()
    }

    fun readBags(path: String): List<Bag> {
        val file = returnFileIfExists(path)

        if (file != null) {
            val result = mutableMapOf<String, Bag>()
            val stringLines = file.readLines()
            for (line in stringLines) {
                val splittedByContain = line.split(" contain ")
                val name = splittedByContain[0].replace(" bags", "")
                result[name] = Bag(name)
            }
            for (line in stringLines) {
                val splittedByContain = line.split(" contain ")
                if (splittedByContain[1] != "no other bags.") {
                    val name = splittedByContain[0].replace(" bags", "")
                    val separateContainsLine = splittedByContain[1].split(", ")
                    for (bag in separateContainsLine) {
                        val words = bag.split(" ")
                        val containsName = words[1] + " " + words[2]
                        if (result.containsKey(name) && result.containsKey(containsName)) {
                            result[name]!!.addBag(result[containsName]!!, words[0].toInt())
                        }
                    }
                }
            }
            return result.values.toList()
        }
        return emptyList()
    }

    fun readProgram(path: String): List<Operation> {
        val file = returnFileIfExists(path)

        if (file != null) {
            val result = mutableListOf<Operation>()
            val stringLines = file.readLines()
            for (line in stringLines) {
                val splited = line.split(" ")
                result.add(Operation(splited[0], splited[1].toInt()))
            }

            return result
        }

        return emptyList()
    }

    fun readSeatMap(path: String): List<List<SeatState>> {
        val file = returnFileIfExists(path)

        if (file != null) {
            val result = mutableListOf<List<SeatState>>()
            val stringLines = file.readLines()
            for (line in stringLines) {
                result.add(line.toCharArray().map { SeatState.ofSymbol(it) }.toList())
            }
            return result
        }

        return emptyList()
    }

    fun readDirections(path: String): List<Pair<Action, Int>> {
        val file = returnFileIfExists(path)

        if (file != null) {
            val result = mutableListOf<Pair<Action, Int>>()
            val stringLines = file.readLines()
            for (line in stringLines) {
                var action: Action = when (line[0]) {
                    'N' -> Direction.NORTH
                    'E' -> Direction.EAST
                    'W' -> Direction.WEST
                    'S' -> Direction.SOUTH
                    'F' -> Move.FORWARD
                    'L' -> Move.LEFT
                    'R' -> Move.RIGHT
                    else -> throw Exception("Incorrect symbol ${line[0]}")
                }
                result.add(Pair(action, line.substring(1 until line.length).toInt()))
            }
            return result
        }

        return emptyList()
    }

    fun readBusSchedule(path: String): Pair<Int, List<Int>> {
        val file = returnFileIfExists(path)

        if (file != null) {
            val stringLines = file.readLines()
            var timestamp = 0
            val lines = mutableListOf<Int>()
            for (line in stringLines) {
                if (line.contains(",")) {
                    val splitted = line.split(",")
                    for (element in splitted) {
                        if (element != "x") {
                            lines.add(element.toInt())
                        }
                    }
                } else {
                    timestamp = line.toInt()
                }
            }


            return Pair(timestamp, lines)
        }

        return Pair(0, emptyList())
    }

    fun readMemory(path: String): List<Memory> {
        val file = returnFileIfExists(path)

        if (file != null) {
            val stringLines = file.readLines()
            var result = mutableListOf<Memory>()

            var currentMask = mutableListOf<Pair<Int, BitMaskCharacter>>()
            var currentMemoryBanks = mutableListOf<Pair<Int, Int>>()

            for (line in stringLines) {
                if (line.startsWith("mask")) {
                    if (currentMask.size > 0) {
                        result.add(Memory(currentMask, currentMemoryBanks))
                        currentMask = mutableListOf()
                        currentMemoryBanks = mutableListOf()
                    }
                    val maskLine = line.replace("mask = ", "")
                    for (character in 0 until maskLine.length) {
                        currentMask.add(
                            Pair(
                                maskLine.length - 1 - character,
                                BitMaskCharacter.fromChar(maskLine[character])
                            )
                        )

                    }
                } else {
                    val splittedLine = line.split(" = ")
                    currentMemoryBanks.add(
                        Pair(
                            splittedLine[0].replace("mem[", "").replace("]", "").toInt(),
                            splittedLine[1].toInt()
                        )
                    )
                }
            }

            result.add(Memory(currentMask, currentMemoryBanks))
            return result
        }

        return emptyList()
    }

    fun readRulesWithTickets(path: String): Triple<RuleList, Ticket, List<Ticket>> {
        val file = returnFileIfExists(path)

        if (file != null) {
            val stringLines = file.readLines()
            var counter = 0

            val rulesList = mutableListOf<Pair<String, List<Int>>>()
            var endOfRules = false
            while (!endOfRules) {
                if (stringLines[counter] == "") {
                    endOfRules = true
                } else {
                    val withoutName = stringLines[counter].split(": ")
                    val splittedLine = withoutName[1].split(" ")
                    val rule = mutableListOf<Int>()
                    rule.addAll(IntRange(splittedLine[0].split("-")[0].toInt(), splittedLine[0].split("-")[1].toInt()).toList())
                    rule.addAll(IntRange(splittedLine[2].split("-")[0].toInt(), splittedLine[2].split("-")[1].toInt()).toList())
                    rulesList.add(Pair(withoutName[0], rule))
                }
                counter+=1
            }

            val ticketList = mutableListOf<Ticket>()

            counter+=1
            val yourTicket = Ticket(stringLines[counter].split(",").map { it.toInt() })
            counter+=1

            while (counter < stringLines.size) {
                if (!(stringLines[counter].startsWith("nearby")  || stringLines[counter] == (""))) {
                    ticketList.add(Ticket(stringLines[counter].split(",").map { it.toInt() }))
                }
                counter+=1
            }
            return Triple(RuleList(rulesList), yourTicket, ticketList)
        }

        return Triple(RuleList(emptyList()), Ticket(emptyList()), emptyList())
    }

    fun readCubeSlice(path: String): List<List<CubeState>> {
        val file = returnFileIfExists(path)

        if (file != null) {
            val result = mutableListOf<List<CubeState>>()
            val stringLines = file.readLines()
            for (line in stringLines) {
                val currentLine = mutableListOf<CubeState>()
                for (character in line.chars()) {
                    currentLine.add(CubeState.ofSymbol(character.toChar()))
                }
                result.add(currentLine)
            }

            return result
        }
        return emptyList()
    }

    private fun createDocument(documentData: String): Document {
        val elements = documentData.split(" ")
        val documentProperties = mutableMapOf<String, String>()
        for (element in elements) {
            val keyValue = element.split(":")
            documentProperties[keyValue[0]] = keyValue[1]
        }
        return Document(documentProperties)
    }

    private fun returnFileIfExists(path: String): File? {
        val file = File(this::class.java.classLoader.getResource(path).toURI())

        if (file.exists() && file.isFile) {
            return file
        }

        return null
    }

}
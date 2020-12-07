import day2.PasswordWithPolicy
import day4.Document
import day7.Bag
import java.io.File
import java.util.stream.Collectors

class FileReader() {
    fun readIntValuesFromFile(path: String) : List<Int> {
        val file = returnFileIfExists(path)

        if (file != null) {
            val stringLines = file.readLines()
            return stringLines.stream().map { it.toInt() }.collect(Collectors.toList())
        }

        return emptyList()
    }

    fun readPasswordsWithPolicyFromFile(path: String) : List<PasswordWithPolicy> {
        val file = returnFileIfExists(path)

        if (file != null) {
            val stringLines = file.readLines()
            return stringLines.stream()
                .filter{ it != "" }
                .map {
                val separate = it.split(" ")
                val occurences = separate[0].split( "-")
                PasswordWithPolicy(occurences[0].toInt(), occurences[1].toInt(), separate[1][0], separate[2])
            }.collect(Collectors.toList())
        }
        return emptyList()
    }

    fun readSlopeRiderMap(path: String) : List<CharArray> {
        val file = returnFileIfExists(path)

        if (file != null) {
            val stringLines = file.readLines()
            return stringLines.map { it.toCharArray() }
        }

        return emptyList()
    }

    fun readDocuments(path: String) : List<Document> {
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

    fun readSeatCodes(path: String) : List<String> {
        val file = returnFileIfExists(path)

        if (file != null) {
            return file.readLines()
        }

        return emptyList()
    }

    fun readQuestionnaires(path: String) : List<Group> {
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

    fun readBags(path: String) : List<Bag> {
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

    private fun createDocument(documentData: String): Document {
        val elements = documentData.split(" ")
        val documentProperties = mutableMapOf<String, String>()
        for (element in elements) {
            val keyValue = element.split(":")
            documentProperties[keyValue[0]] = keyValue[1]
        }
        return Document(documentProperties)
    }

    private fun returnFileIfExists(path: String) : File? {
        val file = File(this::class.java.classLoader.getResource(path).toURI())

        if (file.exists() && file.isFile) {
            return file
        }

        return null
    }

}
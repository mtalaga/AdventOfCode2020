import day2.PasswordWithPolicy
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

    private fun returnFileIfExists(path: String) : File? {
        val file = File(this::class.java.classLoader.getResource(path).toURI())

        if (file.exists() && file.isFile) {
            return file
        }

        return null
    }

}
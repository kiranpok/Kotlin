
open class Human(val name: String, initialAge: Int) {
    var age: Int = initialAge
        private set
    fun getOlder() {
        this.age++
    }
}

class Student(name: String, age: Int): Human(name, age) {
    var courses: Set<CourseRecord> = setOf()
        private set

    fun addCourse(course: CourseRecord) {
        courses = courses.plus(course)
    }

    fun weightedAverage(courses: Set<CourseRecord> = this.courses): Double =
        if (courses.isEmpty()) 0.0 else
            courses.sumOf { it.credits.toDouble() * it.grade } / courses.sumOf { it.credits }
    fun weightedAverage(year: Int): Double =
        weightedAverage(this.courses.filter { it.yearCompleted == year }.toSet())
    fun minMaxGrades() =
        Pair(if (courses.isEmpty()) 0.0 else courses.minOf { it.grade },
            if (courses.isEmpty()) 0.0 else courses.maxOf { it.grade })
}

data class CourseRecord(val name: String, val yearCompleted: Int, val credits: Int = 5, val grade: Double)

class Major(val name: String) {
    private val students: MutableSet<Student> = mutableSetOf()

    fun addStudent(student: Student) {
        students.add(student)
    }

    private fun minMaxAverage(values: List<Double>) =
        if (values.isEmpty()) Triple(0.0, 0.0, 0.0) else
            Triple(values.min(), values.max(), values.sum()/values.count())

    fun stats() = minMaxAverage(students.map { it.weightedAverage() })
    fun stats(courseName: String) =
        minMaxAverage(students.flatMap { it.courses }.filter { it.name == courseName }.map { it.grade })
}
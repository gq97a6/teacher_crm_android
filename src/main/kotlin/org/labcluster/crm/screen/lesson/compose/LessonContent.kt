package org.labcluster.crm.screen.lesson.compose

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.labcluster.crm.composable.PreviewScaffold
import org.labcluster.crm.composable.WavyDivider
import org.labcluster.crm.cs
import org.labcluster.crm.shared.Mock
import org.labcluster.crm.shared.model.Course
import org.labcluster.crm.shared.model.Student
import org.labcluster.crm.shared.model.Teacher

@Preview
@Composable
private fun Preview() = PreviewScaffold { LessonContent(it) }

@Composable
fun LessonContent(
    paddingValues: PaddingValues = PaddingValues(),
    teacher: Teacher = Teacher("Anna", "Testova"),
    students: List<Student> = Mock.students.shuffled().take(5),
    attendance: List<ToggleableState> = List(5) { ToggleableState(false) },
    hasBegun: Boolean = false,
    isEditable: Boolean = false,
    course: Course = Course("Cloud Computing Essentials"),
    topic: String = "Why AWS is evil",
    onStudentCheckbox: (Int) -> Unit = {}
) {
    LazyColumn(Modifier.padding(paddingValues)) {
        item {
            OutlinedTextField(
                state = rememberTextFieldState(course.name),
                label = { Text("Kurs") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            OutlinedTextField(
                state = rememberTextFieldState(topic),
                label = { Text("Temat") },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            )
        }

        item {
            OutlinedTextField(
                state = rememberTextFieldState(teacher.name + " " + teacher.surname),
                label = { Text("Prowadzący") },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            )
        }

        item {
            WavyDivider(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(bottom = 10.dp)
                    .height(10.dp),
                thickness = 3.dp,
                wavelength = 25.dp
            )
        }

        items(students.size) { index ->
            Row(
                Modifier
                    .heightIn(72.dp)
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .border(1.dp, cs.outline, shape = RoundedCornerShape(5.dp))
                    .clickable(isEditable, onClick = { onStudentCheckbox(index) })
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                FlowRow(Modifier.weight(1f)) {
                    students[index].name.split(" ").forEach { name ->
                        Text(
                            "$name ",
                            fontSize = 20.sp,
                            color = cs.onBackground,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight.Black,
                            modifier = Modifier
                        )
                    }
                    students[index].surname.split(" ").forEach { name ->
                        Text(
                            "$name ",
                            fontSize = 20.sp,
                            color = cs.onBackground,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                        )
                    }
                }

                Spacer(Modifier.width(25.dp))

                if (hasBegun || isEditable) CompositionLocalProvider(
                    LocalMinimumInteractiveComponentSize provides Dp.Unspecified
                ) {
                    TriStateCheckbox(
                        state = attendance.getOrNull(index) ?: ToggleableState.Indeterminate,
                        onClick = { onStudentCheckbox(index) },
                        enabled = isEditable
                    )
                }
            }
        }

        item {
            Spacer(Modifier.height(100.dp))
        }
    }
}
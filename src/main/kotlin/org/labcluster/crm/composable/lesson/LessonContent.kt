package org.labcluster.crm.composable.lesson

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.FlowRow
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
import org.labcluster.crm.PreviewSample
import org.labcluster.crm.composable.shared.WavyDivider
import org.labcluster.crm.cs
import org.labcluster.crm.shared.Mock
import org.labcluster.crm.shared.model.Course
import org.labcluster.crm.shared.model.Student
import org.labcluster.crm.shared.model.Teacher

@Preview
@Composable
private fun Preview() = PreviewSample { LessonContent() }

@Composable
fun LessonContent(
    teacher: Teacher = Teacher("Anna", "Testova"),
    students: List<Student> = Mock.students.shuffled().take(5),
    attendance: List<ToggleableState> = List(5) { ToggleableState(false) },
    hasBegun: Boolean = false,
    isEditable: Boolean = false,
    course: Course = Course("Cloud Computing Essentials"),
    topic: String = "Why AWS is evil",
    onStudentCheckbox: (Int) -> Unit = {}
) {
    LazyColumn(
        Modifier
            .padding(horizontal = 15.dp)
    ) {
        item {
            OutlinedTextField(
                state = rememberTextFieldState(course.name),
                label = { Text("Kurs") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            //DropList(
            //    value = topic,
            //    items = topics.map { it.name },
            //    label = "Temat",
            //    height = 400.dp,
            //    onValueSet = onSetTopic,
            //    readOnly = readOnly,
            //    modifier = Modifier
            //        .fillMaxWidth()
            //        .padding(top = 5.dp)
            //)
            OutlinedTextField(
                state = rememberTextFieldState(topic),
                label = { Text("Temat") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            OutlinedTextField(
                state = rememberTextFieldState(teacher.name + " " + teacher.surname),
                label = { Text("Prowadzący") },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
            )
        }

        item {
            WavyDivider(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .padding(bottom = 10.dp)
                    .height(10.dp),
                thickness = 3.dp,
                wavelength = 25.dp
            )
        }

        items(students.size) { index ->
            Row(
                Modifier
                    .heightIn(70.dp)
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
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
                        state = attendance[index],
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
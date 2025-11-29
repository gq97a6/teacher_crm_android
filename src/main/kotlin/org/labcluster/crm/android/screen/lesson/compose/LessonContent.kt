package org.labcluster.crm.android.screen.lesson.compose

import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.labcluster.crm.android.composable.PreviewScaffold
import org.labcluster.crm.android.composable.WavyDivider
import org.labcluster.crm.android.cs
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
            LessonDetails(
                teacher = teacher,
                course = course,
                topic = topic
            )
        }

        item {
            WavyDivider(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .height(10.dp),
                thickness = 3.dp,
                wavelength = 25.dp
            )
        }

        items(students.size) { index ->
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)
                    .height(60.dp)
                    .clip(RoundedCornerShape(100)),
                onClick = {
                    if (isEditable) onStudentCheckbox(index)
                }
            ) {
                Row(
                    Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "Kurs",
                        tint = cs.secondary,
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                            .size(25.dp)
                    )

                    Text(
                        students[index].name + " " + students[index].surname,
                        fontWeight = FontWeight.Medium,
                        color = cs.tertiary,
                        maxLines = 1,
                        modifier = Modifier
                            .weight(1f)
                            .basicMarquee(
                                spacing = MarqueeSpacing(10.dp),
                                velocity = 60.dp
                            )
                    )

                    Spacer(Modifier.width(10.dp))

                    if (hasBegun || isEditable) TriStateCheckbox(
                        state = attendance.getOrNull(index) ?: ToggleableState.Indeterminate,
                        onClick = { onStudentCheckbox(index) },
                        enabled = isEditable
                    )

                    Spacer(Modifier.width(5.dp))
                }
            }
        }

        item {
            Spacer(Modifier.height(300.dp))
        }
    }
}
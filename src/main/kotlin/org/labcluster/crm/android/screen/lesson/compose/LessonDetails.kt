package org.labcluster.crm.android.screen.lesson.compose

import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.Topic
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.labcluster.crm.android.composable.PreviewScaffold
import org.labcluster.crm.android.cs
import org.labcluster.crm.shared.model.Course
import org.labcluster.crm.shared.model.Teacher

@Preview
@Composable
private fun Preview() = PreviewScaffold { LessonDetails(it) }

@Composable
fun LessonDetails(
    paddingValues: PaddingValues = PaddingValues(),
    teacher: Teacher = Teacher("Anna", "Testova"),
    course: Course = Course("Cloud Computing Essentials"),
    topic: String = "Why AWS is evil",
) {
    Column(Modifier.padding(paddingValues)) {
        //Course card
        ElevatedCard(
            Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.School,
                    contentDescription = "Kurs",
                    tint = cs.secondary,
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .size(25.dp)
                )

                Text(
                    course.name,
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

                Spacer(Modifier.width(15.dp))
            }
        }

        Spacer(Modifier.height(15.dp))

        //Topic card
        ElevatedCard(
            Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Topic,
                    contentDescription = "Temat",
                    tint = cs.secondary,
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .size(25.dp)
                )

                Text(
                    topic,
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

                Spacer(Modifier.width(15.dp))
            }
        }

        Spacer(Modifier.height(15.dp))

        //Teacher card
        ElevatedCard(
            Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = "Prowadzący",
                    tint = cs.secondary,
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .size(25.dp)
                )

                Text(
                    teacher.name + " " + teacher.surname,
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

                Spacer(Modifier.width(15.dp))
            }
        }
    }

}
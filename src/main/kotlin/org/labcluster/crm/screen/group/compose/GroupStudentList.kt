package org.labcluster.crm.screen.group.compose

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.labcluster.crm.composable.PreviewScaffold
import org.labcluster.crm.cs
import org.labcluster.crm.shared.Mock
import org.labcluster.crm.shared.model.Student

@Preview
@Composable
private fun Preview() = PreviewScaffold(showAppBar = false) { GroupStudentList() }

@Composable
fun GroupStudentList(
    paddingValues: PaddingValues = PaddingValues(15.dp),
    students: List<Student> = Mock.students.take(10)
) {
    val horizontal = remember { paddingValues.calculateStartPadding(LayoutDirection.Ltr) }

    LazyColumn(
        Modifier
            .padding(top = 10.dp)
            .padding(horizontal = horizontal)
    ) {
        items(students) { student ->
            Row(
                modifier = Modifier
                    .heightIn(72.dp)
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .border(1.dp, cs.outline, shape = RoundedCornerShape(5.dp))
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                FlowRow(Modifier.weight(1f)) {
                    student.name.split(" ").forEach { name ->
                        Text(
                            "$name ",
                            fontSize = 20.sp,
                            color = cs.onBackground,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight.Black,
                            modifier = Modifier
                        )
                    }
                    student.surname.split(" ").forEach { name ->
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
            }
        }
        item {
            Spacer(Modifier.height(100.dp))
        }
    }
}
package com.example.authenticationapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp
import com.example.authenticationapp.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    keyboardType: KeyboardType,
    isPassword: Boolean = false,
) {
    Row(
        modifier = modifier.padding(vertical = 4.dp)
    ) {
        Column {
            Text(
                label,
                style = MaterialTheme.typography.labelSmall
            )
            BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
                val maxWidth = this.maxWidth
                // https://www.jetpackcompose.net/textfield-in-jetpack-compose
                OutlinedTextField(
                    value = value,
                    onValueChange = {
                            it -> onValueChange(it)
                    },
                    modifier = modifier
                        .width(maxWidth)
                        .height(56.dp)
                        .background(
                            MaterialTheme.colorScheme.background,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                    shape = RoundedCornerShape(12.dp),
                )
            }
            if (isPassword) {
                val labelForgotPassword = stringResource(R.string.label_forgot_password)
                Text(
                    buildAnnotatedString {
                        withLink(
                            LinkAnnotation.Url(
                                "https://developer.android.com/jetpack/compose",
                            )
                        ) {append(labelForgotPassword)}
                    },
                    modifier
                        .padding(horizontal = 8.dp, vertical = 2.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

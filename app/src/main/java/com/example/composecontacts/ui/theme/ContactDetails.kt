package com.example.composecontacts.ui.theme
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composecontacts.domain.model.Contact
import com.example.composecontacts.R
import kotlin.collections.component1

@Composable
fun ContactDetails(contact: Contact, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Logo(contact)
            Spacer(24.dp)
            Name(contact)
            Spacer(24.dp)
            ContactList(contact)
        }
    }
}

@Composable
fun Spacer(height: Dp) {
    Spacer(modifier = Modifier.height(height))
}
@Composable
fun Logo(contact: Contact) {
    Box(
        modifier = Modifier
            .height(80.dp)
    ) {
        if (contact.imageRes != null) {
            Image(
                painter = painterResource(id = contact.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight(),
                contentScale = ContentScale.FillHeight
            )
        } else {
            val initials = buildString {
                append(contact.name.take(1))
                append(contact.familyName.take(1))
            }.uppercase()
            val color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
            Canvas(modifier = Modifier.size(100.dp)) {
                drawCircle(
                    color = color,
                    radius = size.minDimension / 2
                )
            }
            Text(
                text = initials,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.align(Alignment.Center)

            )
        }
    }
}
@Composable
fun Name(contact: Contact) {
    Text(
        text = "${contact.name} ${contact.surname ?: ""}".trim(),
        style = MaterialTheme.typography.titleMedium,
    )

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = contact.familyName,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        if (contact.isFavorite) {
            Icon(
                painter = painterResource(android.R.drawable.star_big_on),
                contentDescription = "Favorite",
                tint = Color.Red,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
@Composable
fun ContactList(contact: Contact) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(vertical = 4.dp)
    ) {
        mapOf(
            R.string.phone to contact.phone,
            R.string.address to contact.address,
            R.string.email to contact.email
        ).forEach { (key, value) ->
            if (value != null) {
                InfoRow(
                    label = stringResource(id = key),
                    value = value
                )
            }
        }
    }
}
@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$label: ",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F),
            textAlign = TextAlign.Right,
            style = MaterialTheme.typography.bodyMedium)

        Text(
            text = value,
            modifier = Modifier
                .weight(1F),
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NoPhotoPreview() {
    val contact = Contact(
        name = "Евгений",
        surname = "Андреевич",
        familyName = "Лукашин",
        isFavorite = true,
        phone = "+7 495 495 95 95",
        address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
        email = "ELukashin@practicum.ru"
    )
    ContactDetails(contact = contact)
}

@Preview(showBackground = true, name = "with photo")
@Composable
fun PhotoPreview() {
    val contact = Contact(
        name = "Василий",
        familyName = "Кузякин",
        isFavorite = false,
        phone = "-",
        address = "Ивановская область, дер. Крутово, д.4",
        imageRes = R.drawable.portrait,
        email = null
    )
    ContactDetails(contact = contact)
}

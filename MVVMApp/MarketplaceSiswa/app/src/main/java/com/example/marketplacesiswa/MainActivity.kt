package com.example.marketplacesiswa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// ==========================
// DATA MODEL
// ==========================

data class Product(
    val id: Long = System.currentTimeMillis(),
    val name: String,
    val price: String,
    val description: String,
    val category: String = "UMKM"
)

// ==========================
// MAIN ACTIVITY
// ==========================

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MarketplaceTheme {
                MainScreen()
            }
        }
    }
}

// ==========================
// MAIN SCREEN
// ==========================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    var currentScreen by remember {
        mutableStateOf("home")
    }

    val productList = remember {
        mutableStateListOf<Product>()
    }

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val scope = rememberCoroutineScope()

    // DATA AWAL
    LaunchedEffect(Unit) {

        if (productList.isEmpty()) {

            productList.add(
                Product(
                    name = "Brownies Lumer",
                    price = "15000",
                    description = "Cokelat melimpah, cocok untuk camilan sore.",
                    category = "MAKANAN"
                )
            )

            productList.add(
                Product(
                    name = "Kaos Custom",
                    price = "85000",
                    description = "Bahan adem dan nyaman digunakan.",
                    category = "FASHION"
                )
            )
        }
    }

    Scaffold(

        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },

        topBar = {

            CenterAlignedTopAppBar(

                title = {

                    Text(
                        text = if (currentScreen == "add")
                            "Tambah Produk"
                        else
                            "MarketSiswa",
                        fontWeight = FontWeight.Bold
                    )
                },

                navigationIcon = {

                    if (currentScreen == "add") {

                        IconButton(
                            onClick = {
                                currentScreen = "home"
                            }
                        ) {

                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                }
            )
        },

        bottomBar = {

            NavigationBar {

                NavigationBarItem(
                    selected = currentScreen == "home",

                    onClick = {
                        currentScreen = "home"
                    },

                    icon = {
                        Icon(Icons.Default.Home, null)
                    },

                    label = {
                        Text("Home")
                    }
                )

                NavigationBarItem(
                    selected = currentScreen == "profile",

                    onClick = {
                        currentScreen = "profile"
                    },

                    icon = {
                        Icon(Icons.Default.Person, null)
                    },

                    label = {
                        Text("Profile")
                    }
                )
            }
        },

        floatingActionButton = {

            if (currentScreen == "home") {

                ExtendedFloatingActionButton(

                    onClick = {
                        currentScreen = "add"
                    }

                ) {

                    Icon(Icons.Default.Add, null)

                    Spacer(modifier = Modifier.width(8.dp))

                    Text("Jual")
                }
            }
        }

    ) { innerPadding ->

        Box(
            modifier = Modifier.padding(innerPadding)
        ) {

            when (currentScreen) {

                "home" -> {

                    HomeScreen(products = productList)
                }

                "add" -> {

                    AddProductScreen(

                        onProductAdded = { newProduct ->

                            productList.add(0, newProduct)

                            scope.launch {

                                currentScreen = "home"

                                snackbarHostState.showSnackbar(
                                    "Produk berhasil ditambahkan!"
                                )
                            }
                        }
                    )
                }

                "profile" -> {

                    ProfileScreen()
                }
            }
        }
    }
}

// ==========================
// HOME SCREEN
// ==========================

@Composable
fun HomeScreen(products: List<Product>) {

    if (products.isEmpty()) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Text(
                "Belum ada produk",
                color = Color.Gray
            )
        }

        return
    }

    LazyColumn(

        modifier = Modifier.fillMaxSize(),

        contentPadding = PaddingValues(16.dp),

        verticalArrangement = Arrangement.spacedBy(12.dp)

    ) {

        item {

            Text(
                text = "Halo, Siswa! 👋",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Temukan produk kreatif dari temanmu.",
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(12.dp))
        }

        items(products) { product ->

            ProductCard(product)
        }
    }
}

// ==========================
// PRODUCT CARD
// ==========================

@Composable
fun ProductCard(product: Product) {

    Card(
        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(20.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceBetween,

                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            MaterialTheme.colorScheme.primaryContainer
                        )
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {

                    Text(
                        product.category,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = "Rp ${product.price}",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Black,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                product.name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                product.description,
                color = Color.DarkGray,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {

                Text("Lihat Detail")
            }
        }
    }
}

// ==========================
// ADD PRODUCT SCREEN
// ==========================

@Composable
fun AddProductScreen(
    onProductAdded: (Product) -> Unit
) {

    var name by remember { mutableStateOf("") }

    var price by remember { mutableStateOf("") }

    var desc by remember { mutableStateOf("") }

    var category by remember { mutableStateOf("") }

    var isLoading by remember {
        mutableStateOf(false)
    }

    val scope = rememberCoroutineScope()

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement = Arrangement.spacedBy(16.dp)

    ) {

        OutlinedTextField(

            value = name,

            onValueChange = {
                name = it
            },

            label = {
                Text("Nama Produk")
            },

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(14.dp)
        )

        OutlinedTextField(

            value = category,

            onValueChange = {
                category = it
            },

            label = {
                Text("Kategori")
            },

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(14.dp)
        )

        OutlinedTextField(

            value = price,

            onValueChange = {
                price = it.filter { char ->
                    char.isDigit()
                }
            },

            label = {
                Text("Harga")
            },

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(14.dp)
        )

        OutlinedTextField(

            value = desc,

            onValueChange = {
                desc = it
            },

            label = {
                Text("Deskripsi")
            },

            modifier = Modifier.fillMaxWidth(),

            minLines = 3,

            shape = RoundedCornerShape(14.dp)
        )

        Button(

            onClick = {

                isLoading = true

                scope.launch {

                    delay(1000)

                    onProductAdded(

                        Product(
                            name = name,
                            price = price,
                            description = desc,
                            category = category
                        )
                    )

                    isLoading = false
                }
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),

            enabled =

                name.isNotBlank() &&
                        price.isNotBlank() &&
                        desc.isNotBlank() &&
                        !isLoading,

            shape = RoundedCornerShape(14.dp)

        ) {

            if (isLoading) {

                CircularProgressIndicator(
                    color = Color.White,
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(24.dp)
                )

            } else {

                Text(
                    "Simpan Produk",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

// ==========================
// PROFILE SCREEN
// ==========================

@Composable
fun ProfileScreen() {

    Column(

        modifier = Modifier.fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center

    ) {

        Box(

            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(
                    MaterialTheme.colorScheme.primaryContainer
                ),

            contentAlignment = Alignment.Center

        ) {

            Icon(
                Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier.size(70.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Nama Siswa",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            "XII RPL",
            color = Color.Gray
        )
    }
}

// ==========================
// THEME
// ==========================

@Composable
fun MarketplaceTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(

        colorScheme = lightColorScheme(

            primary = Color(0xFF6750A4),

            secondary = Color(0xFF625B71),

            tertiary = Color(0xFF7D5260)

        ),

        content = content
    )
}
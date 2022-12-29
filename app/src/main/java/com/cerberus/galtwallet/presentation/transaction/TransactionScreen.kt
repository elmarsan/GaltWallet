package com.cerberus.galtwallet.presentation.transaction

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cerberus.galtwallet.R
import com.cerberus.galtwallet.domain.TransactionType
import com.cerberus.galtwallet.presentation.ui.layout.AppScaffold
import com.cerberus.galtwallet.presentation.ui.theme.GreenPrimary
import com.cerberus.galtwallet.presentation.ui.theme.OrangePrimary
import com.cerberus.galtwallet.presentation.ui.theme.WhitePrimary
import com.cerberus.galtwallet.shared.component.LoadingSpinner

@Composable
fun TransactionScreen(
    navController: NavController,
    viewModel: TransactionViewModel = hiltViewModel()
) {
    AppScaffold(
        onGoBack = null,
        content = {
            if (viewModel.loading.value) {
                LoadingSpinner()
            } else {
              LazyColumn {
                  items(viewModel.data.value) { it ->
                      Box(
                          modifier = Modifier
                              .fillMaxWidth()
                              .padding(20.dp)
                              .drawBehind {
                                  drawLine(
                                      color = WhitePrimary,
                                      start = Offset(0f, size.height),
                                      end = Offset(size.width, size.height),
                                      strokeWidth = 2.dp.toPx()
                                  )
                              }
                      ) {
                          Row(modifier =Modifier.fillMaxSize()) {
                              if (it.type == TransactionType.INCOMING) {
                                  Icon(
                                      painter = painterResource(id = R.drawable.ic_baseline_arrow_incoming_24),
                                      contentDescription = null,
                                      tint = GreenPrimary
                                  )

                                  Text(
                                      text = "${stringResource(id = R.string.received)}: ${it.amount.toString()}",
                                      style = MaterialTheme.typography.h4
                                  )
                              } else {
                                  Icon(
                                      painter = painterResource(id = R.drawable.ic_baseline_arrow_outcoming_24),
                                      contentDescription = null,
                                      tint = OrangePrimary
                                  )

                                  Text(
                                      text = "${stringResource(id = R.string.sent)}: ${it.amount.toString()}",
                                      style = MaterialTheme.typography.h4
                                  )
                              }
                          }

                          Row {
                              Text(
                                  text = "${stringResource(id = R.string.transaction_id)} : ${it.txId}",
                                  modifier = Modifier.padding(top = 25.dp, bottom = 5.dp)
                              )
                          }
                      }
                  }
              }
            }
        },
        header = stringResource(id = R.string.transaction_history),
        navController = navController,
        showBottomBar = true,
        showTopBar = true
    )
}

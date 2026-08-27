import SwiftUI
import SharedLogic
import KMPObservableViewModelSwiftUI

struct ContentView: View {
    
    @StateViewModel
    private var viewmodel: FilmListViewModel
    
    init() {
        _viewmodel = StateViewModel(
            wrappedValue: IosKoin.shared.getFilmListViewModel()
        )
    }
        
    var body: some View {
        homePageContent
    }

    @ViewBuilder
    private var homePageContent: some View {
        switch viewmodel.uiState {
        case is FilmListUiStateLoading:
            ProgressView()

        case let success as FilmListUiStateSuccess:
            Text("Films")
                .font(.headline)
            List(success.films, id: \.self) { film in
                VStack(alignment: .leading) {
                    if let title = film.title {
                        Text(title)
                            .font(.title2)
                            .padding()
                    }
                }
            }

        case let error as FilmListUiStateError:
            Text("Error: \(error.message)")

        default:
            EmptyView()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
